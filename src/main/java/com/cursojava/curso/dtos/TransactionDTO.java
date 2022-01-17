package com.cursojava.curso.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.Column;

@Value
public class TransactionDTO {

    Double amount;
    String currency;
    String origin_account;
    String destination_account;
    String description;
}
