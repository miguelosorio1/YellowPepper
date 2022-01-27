package com.cursojava.curso.services;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks // auto-inject AccountRepository
    private AccountService accountService = new AccountServiceImpl(accountRepository);

    @BeforeEach
    void setMockOutput() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(null,"test1", BigDecimal.valueOf(25000)));
        when(accountRepository.findByAccount_number("test1")).thenReturn(accountList);
    }

    @DisplayName("Test Mock findByAccount_number service + AccountRepository")
    @Test
    void getAccountBalance() {
        assertEquals(BigDecimal.valueOf(25000), accountService.getAccountBalance("test1").getAccount_balance());
    }
}