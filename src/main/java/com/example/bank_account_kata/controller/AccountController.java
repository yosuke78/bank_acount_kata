package com.example.bank_account_kata.controller;

import com.example.bank_account_kata.domain.account.model.Account;
import com.example.bank_account_kata.domain.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("add/{clientId}")
    public Account addAccount(@PathVariable String clientId) {
        return accountService.addAccountToClient(clientId);
    }
}
