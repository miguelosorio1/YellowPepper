package com.cursojava.curso.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private BigDecimal amount;
    private String currency;
    private String origin_account;
    private String destination_account;
    private String description;

}
