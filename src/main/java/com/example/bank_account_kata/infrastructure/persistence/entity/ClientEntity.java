package com.example.bank_account_kata.infrastructure.persistence.entity;

import com.example.bank_account_kata.domain.account.model.Account;
import com.example.bank_account_kata.domain.client.model.Client;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @OneToMany
    private List<AccountEntity> accountEntities;

    public ClientEntity() {
    }

    public ClientEntity(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountEntities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AccountEntity> getAccountEntities() {
        return accountEntities;
    }

    public void setAccountEntities(List<AccountEntity> accountEntities) {
        this.accountEntities = accountEntities;
    }

    public Client toDomain() {
        List<Account> accounts = accountEntities.stream().map(AccountEntity::toDomain).toList();

        return new Client(id, firstName, lastName, accounts);
    }
}
