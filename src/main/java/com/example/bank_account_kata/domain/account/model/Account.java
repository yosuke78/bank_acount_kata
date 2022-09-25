package com.example.bank_account_kata.domain.account.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private String id;
    private BigDecimal balance;

    public Account() {
    }

    public Account(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
