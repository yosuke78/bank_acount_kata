package com.example.bank_account_kata.domain.history.service;

import com.example.bank_account_kata.domain.history.model.History;

import java.util.List;

public interface HistoryService {
    List<History> getHistoryForAccount(String accountId);
    History saveHistory(History history);
}
