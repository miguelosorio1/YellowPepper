package com.cursojava.curso.dtos;

import lombok.*;

@Data
public class TransactionDTO {

    private Double amount;
    private String currency;
    private String origin_account;
    private String destination_account;
    private String description;

}
