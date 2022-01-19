package com.cursojava.curso.mappers;

import com.cursojava.curso.dtos.TransactionDTO;
import com.cursojava.curso.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    /** public TransactionDTO toDto(Transaction transaction) {
        String name = transaction.getName();
        List<String> roles = transaction
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(toList());

        return new TransactionDTO(name, roles);
    }
     **/

    public Transaction toEntity(TransactionDTO transactionDTO) {
        return new Transaction(transactionDTO.getAmount(), transactionDTO.getCurrency(), transactionDTO.getOrigin_account(),
                transactionDTO.getDestination_account(), transactionDTO.getDescription());
    }
}
