package com.example.bank_account_kata.domain.client.service.impl;

import com.example.bank_account_kata.domain.client.model.Client;
import com.example.bank_account_kata.domain.client.service.ClientService;
import com.example.bank_account_kata.infrastructure.persistence.entity.ClientEntity;
import com.example.bank_account_kata.infrastructure.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<Client> getClientById(String id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        return clientEntity.map(ClientEntity::toDomain);
    }

    @Override
    public Client createClient(Client client) {
        ClientEntity clientEntity = new ClientEntity(client.getId(), client.getFirstName(), client.getLastName());
        return clientRepository.save(clientEntity).toDomain();
    }
}
