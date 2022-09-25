package com.example.bank_account_kata.domain.account.service;

import com.example.bank_account_kata.domain.account.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Account addAccountToClient(String clientId);
    Optional<BigDecimal> getBalance(String accountId);
    Account deposit(String accountId, BigDecimal amount);
    Account withdraw(String accountId, BigDecimal ammount);
}
