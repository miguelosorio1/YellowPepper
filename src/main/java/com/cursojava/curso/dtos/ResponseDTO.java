package com.cursojava.curso.dtos;

import com.cursojava.curso.models.Transaction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponseDTO {

    String status;
    List<String> errors;
    BigDecimal tax_collected;
    BigDecimal CAD;


}
