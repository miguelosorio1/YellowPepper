package com.cursojava.curso.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Data
public class AccountDTO {

    @Getter @Setter
    String account_number;
    @Getter @Setter
    Double account_balance;

}
