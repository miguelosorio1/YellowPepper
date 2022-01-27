package com.cursojava.curso.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "accounts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @Column(name = "account_number")
    private String account_number;

    @Column(name = "account_balance")
    private BigDecimal account_balance;

}
