package com.example.bank_account_kata.infrastructure.persistence.entity;

import com.example.bank_account_kata.domain.account.model.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    private String id;
    private BigDecimal balance;

    public AccountEntity() {
        this.id = UUID.randomUUID().toString();
        this.balance = BigDecimal.ZERO;
    }

    public AccountEntity(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account toDomain() {
        return new Account(id, balance);
    }
}
