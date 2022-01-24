package com.cursojava.curso.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "transfers")
@Entity
@Data
public class Transfer implements Serializable {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "origin_account")
    private String origin_account;

    @Column(name = "destination_account")
    private String destination_account;

    @Column(name = "transaction_id")
    private Long transaction_id;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}
