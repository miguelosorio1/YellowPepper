package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //List<Account> getAccountList();
    //Double getAccountBalance(String account_number);
    //Account findByAccount_number (String account_number);
}
