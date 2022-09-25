package com.example.bank_account_kata.infrastructure.persistence.entity;

import com.example.bank_account_kata.domain.history.model.BankOperation;
import com.example.bank_account_kata.domain.history.model.History;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    private String id;
    private String accountId;
    private BankOperation operation;
    private BigDecimal amount;
    private LocalDateTime date;

    public HistoryEntity() {
    }

    public HistoryEntity(String accountId, BankOperation operation, BigDecimal amount, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.accountId = accountId;
        this.operation = operation;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BankOperation getOperation() {
        return operation;
    }

    public void setOperation(BankOperation operation) {
        this.operation = operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public History toDomain() {
        return new History(id, accountId, operation, amount, date);
    }
}
