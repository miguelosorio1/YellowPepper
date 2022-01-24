package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "accounts")
@Entity
@Data
public class Account implements Serializable {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "account_number")
    private String account_number;

    @Column(name = "account_balance")
    private Double account_balance;

}
