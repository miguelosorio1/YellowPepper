package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ErrorDTO;
import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.models.Transfer;
import com.cursojava.curso.repositories.AccountRepository;
import com.cursojava.curso.repositories.TransactionRepository;
import com.cursojava.curso.repositories.TransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    public final static Double TAX_ON_TRANSFERS = 0.05;
    public final static Double TAX_COMMON = 0.02;

    TransactionRepository transactionRepo;
    TransfersRepository transfersRepo;
    AccountRepository accountRepo;
    ErrorDTO errorDTO;

    @Override
    public ResponseDTO transfer(Transaction transaction) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        boolean checkTransfer = false;
        try {
            checkTransfer = checkTransfersDay(transaction);

            double taxes = taxCollected(transaction);


            if(checkTransfer){
                // Less than 3
                Transfer transfer = new Transfer();
                boolean transactionDone = applyTransaction(taxes, transaction);
                transfer.setTransaction_id(transaction.getId());
                transfer.setDestination_account(transaction.getDestination_account());
                transfer.setOrigin_account(transaction.getOrigin_account());
                transfer.setTimestamp(new Timestamp(System.currentTimeMillis()));
                transfersRepo.save(transfer);

                responseDTO.setStatus(HttpStatus.OK.toString());
                responseDTO.setTax_collected(taxes);
                responseDTO.setErrors(errors);
                responseDTO.setCAD(convertToCAD());
                System.out.println(transaction.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
            errors.add(e.getMessage());
            responseDTO.setStatus(ErrorDTO.STATUS_ERROR);
            responseDTO.setTax_collected(0.00);
            responseDTO.setErrors(null);
            return responseDTO;

        }

        return responseDTO;

    }

    private Double convertToCAD() {
        return 0.00;
    }

    private boolean applyTransaction(double tax, Transaction transaction) throws Exception{
        Account account = accountRepo.encontrarByAccount_number(transaction.getOrigin_account()).get(0);
        account.setAccount_balance(account.getAccount_balance()-transaction.getAmount());
        account.setAccount_balance(account.getAccount_balance()-tax);
        if(account.getAccount_balance()<0){
            throw new Exception(ErrorDTO.INSUFFICIENT_FUNDS);
        }
        transactionRepo.save(transaction);
        accountRepo.save(account);
        return true;
    }

    @Override
    public boolean checkTransfersDay(Transaction transaction) throws Exception{
        List<Transfer> transfers = transfersRepo.findAll();
        // TODO
        //List<Transfer> transfersToday = new ArrayList<>();

        Timestamp actual=new Timestamp(System.currentTimeMillis());
        Date actualDate=new Date(actual.getTime());
        LocalDate localDate1 = actualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        System.out.println("actualDate " + actualDate);
        int counter = 0;
        if(transfers.isEmpty()){
            System.out.println("Is true");
            return true;
        }else{
            for (Transfer t:transfers) {
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
            throw new Exception(ErrorDTO.LIMIT_EXCEEDED);
        }else{
            return true;
        }
    }

    public Double taxCollected(Transaction transaction){
        return transaction.getAmount() > 100.0 ? transaction.getAmount()*TAX_ON_TRANSFERS : transaction.getAmount()*TAX_COMMON;
    }

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepo, TransfersRepository transfersRepo, AccountRepository accountRepo, ErrorDTO errorDTO) {
        this.transactionRepo = transactionRepo;
        this.transfersRepo = transfersRepo;
        this.errorDTO = errorDTO;
        this.accountRepo = accountRepo;
    }
}
