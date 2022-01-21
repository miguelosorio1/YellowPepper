package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Transaction;

public interface TransactionService {

    ResponseDTO transfer(Transaction transaction);
    boolean checkTransfersDay(Transaction transaction)throws Exception;
}
