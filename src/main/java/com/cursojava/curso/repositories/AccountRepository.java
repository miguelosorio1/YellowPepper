package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //List<Account> getAccountList();
    //Double getAccountBalance(String account_number);
    @Query("SELECT t FROM Account t WHERE t.account_number = ?1")
    List<Account> findByAccount_number(@Param("account_number")String account_number);
}
