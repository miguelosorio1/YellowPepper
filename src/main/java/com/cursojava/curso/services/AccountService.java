package com.cursojava.curso.services;

import com.cursojava.curso.dtos.AccountDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.repositories.AccountRepository;

public interface AccountService {

    public AccountDTO getAccountBalance(String account_number);
}
