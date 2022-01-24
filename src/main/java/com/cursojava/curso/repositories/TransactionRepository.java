package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
