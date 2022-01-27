package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "origin_account")
    private String origin_account;

    @Column(name = "destination_account")
    private String destination_account;

    @Column(name = "description")
    private String description;


}
