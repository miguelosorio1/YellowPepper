package com.cursojava.curso.controllers;

import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.mappers.TransactionMapper;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private TransactionService transactionService;
    private TransactionMapper mapper;

    @RequestMapping(value = "transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO transfer(@RequestBody final TransactionDTO transactionDTO){
        System.out.println(transactionDTO);
        Transaction transaction = mapper.toEntity(transactionDTO);
        System.out.println(transaction.getCurrency());

        return transactionService.transfer(transaction);
    }


    @Autowired
    public TransactionController(TransactionService transactionService, TransactionMapper mapper) {
        this.transactionService = transactionService;
        this.mapper = mapper;
    }
}
