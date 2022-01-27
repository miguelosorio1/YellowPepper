package com.cursojava.curso.controllers;

import com.cursojava.curso.dtos.AccountDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "account", params = {"account_number"},method = RequestMethod.GET)
    public AccountDTO getAccountBalance(@RequestParam(value = "account_number") String account_number){
        return accountService.getAccountBalance(account_number);
    }

}
