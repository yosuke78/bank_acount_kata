package com.example.bank_account_kata.controller;

import com.example.bank_account_kata.domain.client.model.Client;
import com.example.bank_account_kata.domain.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("create")
    public ResponseEntity<Client> createClient(@RequestParam String firstName, @RequestParam String lastName) {
        Client client = new Client(firstName, lastName);

        return ResponseEntity.ok(clientService.createClient(client));
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable String id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
