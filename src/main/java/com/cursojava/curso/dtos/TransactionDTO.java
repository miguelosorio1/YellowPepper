package com.cursojava.curso.dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class TransactionDTO {

    Double amount;
    String currency;
    String origin_account;
    String destination_account;
    String description;

    public Double getAmount() {
        return amount;
    }
}
