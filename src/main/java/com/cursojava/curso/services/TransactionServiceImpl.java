package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ErrorDTO;
import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.models.Transfer;
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
    List<ErrorDTO> errors;

    @Override
    public ResponseDTO transfer(Transaction transaction) {


        boolean checkTransfer = false;
        try {
            checkTransfer = checkTransfersDay(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Double taxes = taxCollected(transaction);

        ResponseDTO responseDTO = new ResponseDTO();

        if(checkTransfer){
            // Less than 3
            responseDTO.setStatus(HttpStatus.OK.toString());
            responseDTO.setTax_collected(taxes);
            responseDTO.setErrors(null);
            responseDTO.setCAD(0.0);
            System.out.println(transaction.toString());
        }else{
            // More than 3
            responseDTO.setStatus("ERROR");
            responseDTO.setTax_collected(0.00);
            responseDTO.setErrors(null);
        }


        return responseDTO;
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
            return false;
        }else{
            return true;
        }
    }

    public Double taxCollected(Transaction transaction){
        return transaction.getAmount() > 100.0 ? transaction.getAmount()*TAX_ON_TRANSFERS : transaction.getAmount()*TAX_COMMON;
    }

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepo, TransfersRepository transfersRepo, List<ErrorDTO> errors) {
        this.transactionRepo = transactionRepo;
        this.transfersRepo = transfersRepo;
        this.errors = errors;
    }
}
