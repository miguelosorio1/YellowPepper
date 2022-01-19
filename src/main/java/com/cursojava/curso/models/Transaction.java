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
    private Long id;

    @Getter @Setter @Column(name = "amount")
    private Double amount;

    @Getter @Setter @Column(name = "currency")
    private String currency;

    @Getter @Setter @Column(name = "origin_account")
    private String origin_account;

    @Getter @Setter @Column(name = "destination_account")
    private String destination_account;

    @Getter @Setter @Column(name = "description")
    private String description;

    public Transaction(Double amount, String currency, String origin_account, String destination_account, String description) {
    }
}
