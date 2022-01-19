package com.cursojava.curso.dtos;

import com.cursojava.curso.models.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

@Value
public class ResponseDTO {

    @Getter @Setter
    String status;
    @Getter @Setter
    List<ErrorDTO> errors;
    @Getter @Setter
    Double tax_collected;
    @Getter @Setter @Nullable
    Double CAD;


}
