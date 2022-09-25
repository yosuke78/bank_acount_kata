package com.example.bank_account_kata.infrastructure.persistence.repository;

import com.example.bank_account_kata.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}
