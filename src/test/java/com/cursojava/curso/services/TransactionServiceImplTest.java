package com.cursojava.curso.services;

import com.cursojava.curso.models.Account;
import com.cursojava.curso.models.Transaction;
import com.cursojava.curso.models.Transfer;
import com.cursojava.curso.repositories.AccountRepository;
import com.cursojava.curso.repositories.TransactionRepository;
import com.cursojava.curso.repositories.TransfersRepository;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransfersRepository transfersRepository;
    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository, transfersRepository, accountRepository, restTemplate);


    @BeforeEach
    void setMockOutput() {
        List<Account> accountList1 = new ArrayList<>();
        List<Account> accountList2 = new ArrayList<>();

        accountList1.add(new Account(1L,"test1", BigDecimal.valueOf(25000)));
        accountList2.add(new Account(2L,"test2", BigDecimal.valueOf(80000)));

        when(accountRepository.findByAccount_number("test1")).thenReturn(accountList1);
        when(accountRepository.findByAccount_number("test2")).thenReturn(accountList2);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, BigDecimal.valueOf(15000), "USD", "test2", "test1", "testing" ));
        transactionList.add(new Transaction(2L, BigDecimal.valueOf(2500), "USD", "test1", "test2", "testing" ));
        transactionList.add(new Transaction(3L, BigDecimal.valueOf(3500), "USD", "test1", "test2", "testing" ));
        transactionList.add(new Transaction(4L, BigDecimal.valueOf(4500), "USD", "test1", "test2", "testing" ));
        // TODO
        //when(transactionRepository.findBy("test2")).thenReturn(transactionList);

        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(1L, "test2", "test1", 1L, new Timestamp(System.currentTimeMillis())));

        List<Transfer> transfers1 = new ArrayList<>();
        transfers1.add(new Transfer(2L, "test1", "test2", 2L, new Timestamp(System.currentTimeMillis())));
        transfers1.add(new Transfer(3L, "test1", "test2", 3L, new Timestamp(System.currentTimeMillis())));
        transfers1.add(new Transfer(4L, "test1", "test2", 4L, new Timestamp(System.currentTimeMillis())));
        when(transfersRepository.findByOrigin_account("test2")).thenReturn(transfers);
        when(transfersRepository.findByOrigin_account("test1")).thenReturn(transfers1);

        // EXTERNAL API
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("timestamp", new Timestamp(System.currentTimeMillis()));
        json.put("base", "EUR");
        json.put("date", new Date(new Timestamp(System.currentTimeMillis()).getTime()));

        JSONObject rates = new JSONObject();
        rates.put("EUR", 1);
        rates.put("USD", 1.12455);
        rates.put("CAD", 1.428289);

        json.put("rates", rates);

        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                        requestTo("http://api.exchangeratesapi.io/v1/latest?access_key=7613194f5b49aa7d9ae9bf3a5eb12ab1&base=EUR&symbols=EUR,CAD,USD"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json.toString())
                );
        /**
        Mockito
                .when(restTemplate.getForEntity(
                        "http://api.exchangeratesapi.io/v1/latest?access_key=7613194f5b49aa7d9ae9bf3a5eb12ab1&base=EUR&symbols=EUR,CAD,USD"
                        , JSONObject.class))
          .thenReturn(new ResponseEntity(json, HttpStatus.OK));
        **/
    }

    @Test
    void transfer() {
        Transaction transaction1 = new Transaction(null, BigDecimal.valueOf(2500), "USD", "test1", "test2", "Description");
        System.out.println(transactionService.transfer(transaction1).getErrors());
        assertEquals("ERROR", transactionService.transfer(transaction1).getStatus());

        Transaction transaction2 = new Transaction(null, BigDecimal.valueOf(2500), "USD", "test2", "test1", "Description");
        System.out.println(transactionService.transfer(transaction2).getErrors());
        assertEquals("OK", transactionService.transfer(transaction2).getStatus());

    }

    @SneakyThrows
    @Test
    void convertToCAD() {
        try {
            BigDecimal toCad = transactionService.convertToCAD(BigDecimal.valueOf(5000));
            assertEquals(BigDecimal.valueOf(6367.722060700000), toCad);
        }catch (Exception e){

        }
    }

    @SneakyThrows
    @Test
    void checkTransfersDay() {
        try{
            Transaction transaction1 = new Transaction(null, BigDecimal.valueOf(2500), "USD", "test2", "test1", "Description");
            assertEquals(true, transactionService.checkTransfersDay(transaction1));
        }catch (Exception e){

        }
    }
}