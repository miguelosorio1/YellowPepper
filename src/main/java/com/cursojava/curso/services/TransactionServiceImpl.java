package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Transaction;

public class TransactionServiceImpl implements TransactionService{


    @Override
    public ResponseDTO transfer(TransactionDTO transaction) {
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(transaction.getAmount());
        transaction1.setCurrency(transaction.getCurrency());
        transaction1.setOrigin_account(transaction.getOrigin_account());
        transaction1.setDestination_account(transaction.getDestination_account());
        transaction1.setDescription(transaction.getDescription());

        ResponseDTO responseDTO;

        return null;
    }

    @Override
    public int verifyAttempt(TransactionDTO transaction) {
        return 0;
    }

}
