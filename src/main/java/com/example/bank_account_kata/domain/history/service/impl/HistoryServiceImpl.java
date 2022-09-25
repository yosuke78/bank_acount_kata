package com.example.bank_account_kata.domain.history.service.impl;

import com.example.bank_account_kata.domain.history.model.History;
import com.example.bank_account_kata.domain.history.service.HistoryService;
import com.example.bank_account_kata.infrastructure.persistence.entity.HistoryEntity;
import com.example.bank_account_kata.infrastructure.persistence.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<History> getHistoryForAccount(String accountId) {
        return historyRepository.findAllByAccountId(accountId)
                                .stream()
                                .map(HistoryEntity::toDomain)
                                .sorted(Comparator.comparing(History::getDate).reversed())
                                .collect(Collectors.toList());
    }

    @Override
    public History saveHistory(History history) {
        HistoryEntity historyEntity = new HistoryEntity(history.getAccountId(),
                                                        history.getOperation(),
                                                        history.getAmount(),
                                                        history.getDate());
        return historyRepository.save(historyEntity).toDomain();
    }
}
