package com.cursojava.curso.controllers;

import com.cursojava.curso.dtos.ResponseDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    public ResponseDTO transfer(@PathVariable TransactionDTO transactionDTO){
        return transactionService.transfer(transactionDTO);
    }

}
