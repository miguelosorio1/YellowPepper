package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, Long> {

    /**
     * @return the last 10 attempts for a given user, identified by their alias. */
    @Query("SELECT a FROM Transfer a WHERE a.origin_account = ?1 ORDER BY a.timestamp DESC")
    List<Transfer> findByOrigin_account(@Param("origin_account")String origin_account);

    List<Transfer> findAll();


}
