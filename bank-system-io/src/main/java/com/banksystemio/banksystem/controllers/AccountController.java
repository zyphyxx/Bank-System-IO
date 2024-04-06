package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody BigDecimal amount) {
        accountService.deposit(amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody BigDecimal amount) {
        accountService.withdraw(amount);
    }



}
