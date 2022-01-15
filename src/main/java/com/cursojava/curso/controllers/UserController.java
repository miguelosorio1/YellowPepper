package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AccountService accountRepo;

    @RequestMapping(value = "prueba")
    public List<String> prueba(){
        return List.of("manzana", "pera", "fresa");
    }

    @RequestMapping(value = "transfer")
    public Transaction transferFunds(){

        return null;
    }

    @RequestMapping(value = "account/{account_number}")
    public Account getAccount(@PathVariable String account_number){
        /** Account origin = new Account();
        origin.setAccount_number(account_number);
        origin.setAccount_balance(500000.0);
         **/
        return null;
    }

    @RequestMapping(value = "accounts")
    public List<Account> getAccountList(){
        return null;
    }


    // Get CURRENCY del API

    // Post con la transferencia
}
