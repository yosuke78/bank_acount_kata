package com.example.bank_account_kata.domain.client.service;

import com.example.bank_account_kata.domain.client.model.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> getClientById(String id);

    Client createClient(Client client);
}
