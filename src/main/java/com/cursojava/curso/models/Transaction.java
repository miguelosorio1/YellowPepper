package com.cursojava.curso.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "transactions")
@Entity
@Data
public class Transaction {

    @Getter @Setter @Column(name = "id")
    @Id
    private int id;

    @Getter @Setter @Column(name = "amount")
    private double amount;

    @Getter @Setter @Column(name = "currency")
    private String currency;

    @Getter @Setter @Column(name = "origin_account")
    private String origin_account;

    @Getter @Setter @Column(name = "destination_account")
    private String destination_account;

    @Getter @Setter @Column(name = "description")
    private String description;

    }
