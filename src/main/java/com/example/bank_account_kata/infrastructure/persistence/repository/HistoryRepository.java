package com.example.bank_account_kata.infrastructure.persistence.repository;

import com.example.bank_account_kata.infrastructure.persistence.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findAllByAccountId(String accountId);
}
