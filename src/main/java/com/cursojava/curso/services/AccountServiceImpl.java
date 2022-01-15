package com.cursojava.curso.services;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepo;

    @Override
    public String transfer(String account_number) {
        Account account = accountRepo.findByAccountNumberEquals(account_number);
        return null;
    }

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }
}
