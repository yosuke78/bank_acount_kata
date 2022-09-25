package com.example.bank_account_kata.domain.client.model;

import com.example.bank_account_kata.domain.account.model.Account;

import java.util.List;
import java.util.UUID;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public Client() {
    }

    public Client(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String id, String firstName, String lastName, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
