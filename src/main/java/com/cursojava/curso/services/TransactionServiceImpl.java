package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ErrorEnum;
import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.models.Transfer;
import com.cursojava.curso.repositories.AccountRepository;
import com.cursojava.curso.repositories.TransactionRepository;
import com.cursojava.curso.repositories.TransfersRepository;
import com.fasterxml.jackson.databind.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.cursojava.curso.dtos.ErrorEnum.*;

@Service
public class TransactionServiceImpl implements TransactionService{

    public final static BigDecimal TAX_ON_TRANSFERS = BigDecimal.valueOf(0.05);
    public final static BigDecimal TAX_COMMON = BigDecimal.valueOf(0.02);

    @Value("${exchangeratesapi.uri}")
    public String host;
    @Value("${exchangeratesapi.access_key}")
    public String access_key;
    @Value("${exchangeratesapi.base}")
    public String base;
    @Value("${exchangeratesapi.symbols}")
    public String symbols;


    TransactionRepository transactionRepo;
    TransfersRepository transfersRepo;
    AccountRepository accountRepo;
    WebClient webClient;

    @Override
    public ResponseDTO transfer(Transaction transaction) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        boolean checkTransfer = false;
        try {
            checkTransfer = checkTransfersDay(transaction);
            BigDecimal taxes = taxCollected(transaction);

            if(checkTransfer){
                // Less than 3
                Transfer transfer = new Transfer();

                applyTransaction(taxes, transaction);

                transfer.setTransaction_id(transaction.getId());
                transfer.setDestination_account(transaction.getDestination_account());
                transfer.setOrigin_account(transaction.getOrigin_account());
                transfer.setTimestamp(new Timestamp(System.currentTimeMillis()));
                transfersRepo.save(transfer);

                responseDTO.setStatus(STATUS_OK.label);
                responseDTO.setTax_collected(taxes);
                responseDTO.setErrors(errors);
                responseDTO.setCAD(convertToCAD(transaction.getAmount()));
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
            responseDTO.setStatus(STATUS_ERROR.label);
            responseDTO.setTax_collected(BigDecimal.valueOf(0.0));
            responseDTO.setErrors(errors);
            System.out.println(e.getStackTrace() + e.getMessage());
            return responseDTO;

        }

        return responseDTO;

    }

    public BigDecimal convertToCAD(BigDecimal amount) throws Exception{

        BigDecimal toCad = BigDecimal.valueOf(0.0);

        RestTemplate restTemplate = new RestTemplate();
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(host)
                .queryParam("access_key", access_key)
                .queryParam("base", base)
                .queryParam("symbols", symbols)
                .encode()
                .toUriString();
        JsonNode jsonResponse = restTemplate.getForObject(
                urlTemplate
                , JsonNode.class);

        System.out.println("JSONResponse " + jsonResponse);
        JsonNode rates = jsonResponse.get("rates");
        System.out.println(rates);

        BigDecimal bigEUR = new BigDecimal(rates.get("EUR").toString());
        BigDecimal bigCAD = new BigDecimal(rates.get("CAD").toString());
        BigDecimal bigUSD = new BigDecimal(rates.get("USD").toString());
        System.out.println(bigUSD);

        BigDecimal eurAmount = (bigEUR.divide(bigUSD, 5, RoundingMode.HALF_UP)).multiply(amount);
        System.out.println(eurAmount);

        toCad = eurAmount.multiply(bigCAD);
        System.out.println(toCad);

        return toCad;
    }

    private void applyTransaction(BigDecimal tax, Transaction transaction) throws Exception{

        List<Account> originList = accountRepo.findByAccount_number(transaction.getOrigin_account());
        if(!originList.isEmpty()){
            Account account = originList.get(0);
            account.setAccount_balance(account.getAccount_balance().subtract(transaction.getAmount()));
            account.setAccount_balance(account.getAccount_balance().subtract(tax));
            if (account.getAccount_balance().compareTo(BigDecimal.valueOf(0)) < 0) {
                throw new Exception(INSUFFICIENT_FUNDS.label);
            }
            List<Account> destinationList = accountRepo.findByAccount_number(transaction.getDestination_account());
            if(!originList.isEmpty()){
                Account destination = originList.get(0);
                destination.setAccount_balance(destination.getAccount_balance().add(transaction.getAmount()));
                transactionRepo.save(transaction);
                accountRepo.save(account);
                accountRepo.save(destination);
            }else{
                throw new Exception(NONEXISTENT_ACCOUNT.label);
            }
        }else{
            throw new Exception(NONEXISTENT_ACCOUNT.label);
        }

    }

    @Override
    public boolean checkTransfersDay(Transaction transaction) throws Exception{
        List<Transfer> transfers = transfersRepo.findByOrigin_account(transaction.getOrigin_account());

        Timestamp actual=new Timestamp(System.currentTimeMillis());
        Date actualDate=new Date(actual.getTime());
        LocalDate localDate1 = actualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int counter = 0;
        if(transfers.isEmpty()){
            return true;
        }else{
            for (Transfer t : transfers) {
                Timestamp ts = t.getTimestamp();
                Date date = new Date(ts.getTime());
                LocalDate localDate2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(localDate1.isEqual(localDate2)){
                    counter++;
                }
                System.out.println("date in loop "+ date);
            }
        }
        if(counter >= 3){
            throw new Exception(LIMIT_EXCEEDED.label);
        }else{
            return true;
        }
    }

    private BigDecimal taxCollected(Transaction transaction){
        return transaction.getAmount().compareTo(BigDecimal.valueOf(100.0)) > 0 ? transaction.getAmount().multiply(TAX_ON_TRANSFERS) : transaction.getAmount().multiply(TAX_COMMON);
    }


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepo, TransfersRepository transfersRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.transfersRepo = transfersRepo;
        this.accountRepo = accountRepo;

    }
}
