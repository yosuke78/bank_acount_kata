package com.example.bank_account_kata.domain.history.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class History {
    private String id;
    private String accountId;
    private BankOperation operation;
    private BigDecimal amount;
    private LocalDateTime date;

    public History() {
    }

    public History(String accountId, BankOperation operation, BigDecimal amount, LocalDateTime date) {
        this.accountId = accountId;
        this.operation = operation;
        this.amount = amount;
        this.date = date;
    }
    public History(String id, String accountId, BankOperation operation, BigDecimal amount, LocalDateTime date) {
        this.id = id;
        this.accountId = accountId;
        this.operation = operation;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public BankOperation getOperation() {
        return operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
