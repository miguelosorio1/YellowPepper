package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction implements Serializable {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "origin_account")
    private String origin_account;

    @Column(name = "destination_account")
    private String destination_account;

    @Column(name = "description")
    private String description;


}
