package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAccountList();
    Double getAccountBalance(String account_number);
    Account findByAccountNumberEquals (String account_number);
}
