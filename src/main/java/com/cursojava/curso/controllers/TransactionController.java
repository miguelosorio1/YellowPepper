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

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionMapper mapper;

    @RequestMapping(value = "transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO transfer(@RequestBody final TransactionDTO transactionDTO){
        Transaction transaction = mapper.toEntity(transactionDTO);

        return transactionService.transfer(transaction);
    }


}
