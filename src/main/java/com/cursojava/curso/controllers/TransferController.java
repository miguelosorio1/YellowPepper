package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.services.AccountService;
import com.cursojava.curso.services.TransferService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private TransferService accountRepo;

    @RequestMapping(value = "accounts")
    public List<Account> transfer(){
        return null;
    }

}
