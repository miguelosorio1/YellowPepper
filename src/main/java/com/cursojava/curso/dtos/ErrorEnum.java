package com.cursojava.curso.dtos;


public enum ErrorEnum {

    INSUFFICIENT_FUNDS ("insufficient-funds"),
    LIMIT_EXCEEDED ("limit-exceeded"),
    NONEXISTENT_ACCOUNT("the account does not exist"),
    DIFFERENT_ACCOUNTS ("The origin account and destination account must be different"),
    STATUS_ERROR ("ERROR"),
    STATUS_OK ("OK");

    public final String label;


    ErrorEnum(String label) {
        this.label = label;
    }
}
