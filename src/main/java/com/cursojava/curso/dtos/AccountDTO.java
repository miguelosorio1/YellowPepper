package com.cursojava.curso.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDTO {


    String status;
    List<String> errors;
    BigDecimal account_balance;

}
