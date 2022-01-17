package com.cursojava.curso.dtos;

import com.cursojava.curso.models.Transaction;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

@Value
public class ResponseDTO {

    String status;
    List<ErrorDTO> errors;
    Double tax_collected;
    @Nullable
    Double CAD;


}
