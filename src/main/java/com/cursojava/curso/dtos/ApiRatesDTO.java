package com.cursojava.curso.dtos;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


public class ApiRatesDTO {
     private boolean success;
     private Timestamp timestamp;
     private String base;
     private Date date;
     private Map<String, Double> rates;


}
