package com.cursojava.curso.models;

public class Transaction {

    private double amount;
    private String currency;
    private String origin_account;
    private String destination_account;
    private String description;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrigin_account() {
        return origin_account;
    }

    public void setOrigin_account(String origin_account) {
        this.origin_account = origin_account;
    }

    public String getDestination_account() {
        return destination_account;
    }

    public void setDestination_account(String destination_account) {
        this.destination_account = destination_account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
