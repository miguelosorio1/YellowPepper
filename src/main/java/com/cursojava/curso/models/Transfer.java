package com.cursojava.curso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "transfers")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
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
