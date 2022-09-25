package com.example.bank_account_kata.controller;

import com.example.bank_account_kata.domain.account.model.Account;
import com.example.bank_account_kata.domain.account.service.AccountService;
import com.example.bank_account_kata.domain.history.model.History;
import com.example.bank_account_kata.domain.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String id) {
        Optional<BigDecimal> balance = accountService.getBalance(id);
        return balance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<Account> depositToAccount(@PathVariable String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.deposit(accountId, amount));
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<Account> withdrawFromAccount(@PathVariable String accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdraw(accountId, amount));
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<History>> getHistoryForAccount(@PathVariable String accountId) {
        return ResponseEntity.ok(historyService.getHistoryForAccount(accountId));
    }
}
