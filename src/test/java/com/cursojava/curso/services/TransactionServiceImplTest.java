package com.cursojava.curso.services;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.models.Transfer;
import com.cursojava.curso.repositories.AccountRepository;
import com.cursojava.curso.repositories.TransactionRepository;
import com.cursojava.curso.repositories.TransfersRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransfersRepository transfersRepository;
    @Mock
    private WebClient webClient;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository, transfersRepository, accountRepository);

    @BeforeEach
    void setMockOutput() {
        List<Account> accountList1 = new ArrayList<>();
        List<Account> accountList2 = new ArrayList<>();

        accountList1.add(new Account(null,"test1", BigDecimal.valueOf(25000)));
        accountList2.add(new Account(null,"test2", BigDecimal.valueOf(80000)));

        when(accountRepository.findByAccount_number("test1")).thenReturn(accountList1);
        when(accountRepository.findByAccount_number("test2")).thenReturn(accountList2);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, BigDecimal.valueOf(15000), "USD", "test2", "test1", "testing" ));
        transactionList.add(new Transaction(2L, BigDecimal.valueOf(2500), "USD", "test1", "test2", "testing" ));
        transactionList.add(new Transaction(3L, BigDecimal.valueOf(3500), "USD", "test1", "test2", "testing" ));
        transactionList.add(new Transaction(4L, BigDecimal.valueOf(4500), "USD", "test1", "test2", "testing" ));
        // TODO
        when(accountRepository.findByAccount_number("test2")).thenReturn(accountList2);

        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(1L, "test2", "test1", 1L, new Timestamp(System.currentTimeMillis())));

        List<Transfer> transfers1 = new ArrayList<>();
        transfers1.add(new Transfer(2L, "test1", "test2", 2L, new Timestamp(System.currentTimeMillis())));
        transfers1.add(new Transfer(3L, "test1", "test2", 3L, new Timestamp(System.currentTimeMillis())));
        transfers1.add(new Transfer(4L, "test1", "test2", 4L, new Timestamp(System.currentTimeMillis())));
        when(transfersRepository.findByOrigin_account("test2")).thenReturn(transfers);
        when(transfersRepository.findByOrigin_account("test1")).thenReturn(transfers1);

    }

    @Test
    void transfer() {
        Transaction transaction1 = new Transaction(null, BigDecimal.valueOf(2500), "USD", "Test2", "Test1", "Description");
        assertEquals("OK", transactionService.transfer(transaction1).getStatus());
    }

    @Test
    void convertToCAD() {

    }

    @SneakyThrows
    @Test
    void checkTransfersDay() {
        Transaction transaction1 = new Transaction(null, BigDecimal.valueOf(2500), "USD", "Test2", "Test1", "Description");
        assertEquals(true, transactionService.checkTransfersDay(transaction1));
    }
}