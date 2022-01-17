package com.cursojava.curso.services;

import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;

public interface TransactionService {

    ResponseDTO transfer(TransactionDTO transaction);
    int verifyAttempt(TransactionDTO transaction);
}
