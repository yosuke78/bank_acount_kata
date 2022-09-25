package com.example.bank_account_kata.infrastructure.persistence.repository;

import com.example.bank_account_kata.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
