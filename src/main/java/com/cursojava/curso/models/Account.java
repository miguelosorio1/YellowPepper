package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "accounts")
@Entity
@Data
public class Account {

    @Getter @Setter @Column(name = "id")
    @Id
    private Long id;

    @Getter @Setter @Column(name = "account_number")
    private String account_number;

    @Getter @Setter @Column(name = "account_balance")
    private Double account_balance;

}
