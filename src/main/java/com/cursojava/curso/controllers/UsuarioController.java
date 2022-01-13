package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "prueba")
    public List<String> prueba(){
        return List.of("manzana", "pera", "fresa");
    }
    @RequestMapping(value = "transfer")
    public Transaction transferFunds(){
        Account origin = new Account();
        origin.setAccount_number("123456");

        Account destination = new Account();
        destination.setAccount_number("098765");

        Transaction transaction = new Transaction();
        transaction.setAmount(5000);
        transaction.setCurrency("USD");
        transaction.setDescription("Hey hey");
        transaction.setOrigin_account(origin.getAccount_number());
        transaction.setDestination_account(destination.getAccount_number());

        return transaction;
    }

    @RequestMapping(value = "account/{account_number}")
    public Account getAccount(@PathVariable String account_number){
        Account origin = new Account();
        origin.setAccount_number(account_number);
        origin.setAccount_balance(500000.0);
        return origin;
    }

    @RequestMapping(value = "accounts")
    public List<Account> getAccountList(){
        List<Account> accountList = new ArrayList<>();

        Account origin1 = new Account();
        origin1.setAccount_number("1233");
        origin1.setAccount_balance(500000.0);

        Account origin2 = new Account();
        origin2.setAccount_number("1244");
        origin2.setAccount_balance(10000.0);

        Account origin3 = new Account();
        origin3.setAccount_number("1255");
        origin3.setAccount_balance(800000.0);

        accountList.add(origin1);
        accountList.add(origin2);
        accountList.add(origin3);

        return accountList;
    }
    // Get CURRENCY del API

    // Post con la transferencia
}
