package com.cursojava.curso.services;

import com.cursojava.curso.dtos.AccountDTO;
import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Account;
import com.cursojava.curso.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.cursojava.curso.dtos.ErrorEnum.*;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepo;

    public AccountDTO getAccountBalance(String origin_account){
        AccountDTO accountDTO = new AccountDTO();
        List<String> errors = new ArrayList<>();
        try {
            List<Account> accountList = accountRepo.findByAccount_number(origin_account);
            if (!accountList.isEmpty()){
                Account account = accountList.get(0);
                accountDTO.setStatus(STATUS_OK.label);
                accountDTO.setAccount_balance(account.getAccount_balance());
                accountDTO.setErrors(errors);
            }else{
                errors.add(NONEXISTENT_ACCOUNT.label);
                accountDTO.setStatus(STATUS_ERROR.label);
                accountDTO.setAccount_balance(BigDecimal.valueOf(0.0));
                accountDTO.setErrors(errors);
            }
            return accountDTO;
        }catch (Exception e){
            errors.add(e.getMessage());
            accountDTO.setStatus(STATUS_ERROR.label);
            accountDTO.setAccount_balance(BigDecimal.valueOf(0.0));
            accountDTO.setErrors(errors);
            return accountDTO;
        }
    }

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }
}
