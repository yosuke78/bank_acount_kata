package com.example.bank_account_kata.domain.account.service.impl;

import com.example.bank_account_kata.domain.account.model.Account;
import com.example.bank_account_kata.domain.account.service.AccountService;
import com.example.bank_account_kata.domain.history.model.BankOperation;
import com.example.bank_account_kata.domain.history.model.History;
import com.example.bank_account_kata.domain.history.service.HistoryService;
import com.example.bank_account_kata.infrastructure.persistence.entity.AccountEntity;
import com.example.bank_account_kata.infrastructure.persistence.entity.ClientEntity;
import com.example.bank_account_kata.infrastructure.persistence.repository.AccountRepository;
import com.example.bank_account_kata.infrastructure.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Account addAccountToClient(String clientId) {
        AccountEntity accountEntity = new AccountEntity();
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(clientId);
        if (clientEntityOptional.isPresent()) {
            ClientEntity clientEntity = clientEntityOptional.get();
            List<AccountEntity> accountEntities = clientEntity.getAccountEntities();
            accountEntities.add(accountEntity);
            clientEntity.setAccountEntities(accountEntities);
            Account account = accountRepository.save(accountEntity).toDomain();
            clientRepository.save(clientEntity);
            return account;
        }
        return null;
    }

    @Override
    public Optional<BigDecimal> getBalance(String accountId) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        return accountEntity.map(AccountEntity::getBalance);
    }

    @Override
    public Account deposit(String accountId, BigDecimal amount) {
        AccountEntity accountEntity = accountRepository.findById(accountId).get();
        accountEntity.setBalance(accountEntity.getBalance().add(amount));
        History history = new History(accountId, BankOperation.DEPOSIT, amount, LocalDateTime.now());
        historyService.saveHistory(history);

        return accountRepository.save(accountEntity).toDomain();
    }

    @Override
    public Account withdraw(String accountId, BigDecimal amount) {
        AccountEntity accountEntity = accountRepository.findById(accountId).get();
        accountEntity.setBalance(accountEntity.getBalance().subtract(amount));
        History history = new History(accountId, BankOperation.WITHDRAW, amount, LocalDateTime.now());
        historyService.saveHistory(history);

        return accountRepository.save(accountEntity).toDomain();
    }
}
