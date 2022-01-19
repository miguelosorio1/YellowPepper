package com.cursojava.curso.services;

import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepo;
    //Account account = accountRepo.findByAccount_number("");

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }
}
