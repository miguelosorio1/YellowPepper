package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, Long> {

    /**
     * @return the last 10 attempts for a given user, identified by their alias. */
    //@Query("SELECT a FROM transfers a WHERE a.account_number = ?1 ORDER BY a.id DESC")
    //List<Transfer> findByOrigin_account(String origin_account);
    List<Transfer> findAll();


}
