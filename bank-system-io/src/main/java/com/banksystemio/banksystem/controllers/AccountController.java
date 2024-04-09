package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.entities.Account;

import com.banksystemio.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> findAllAccounts(){
       return accountService.findAllAccounts();
    }
    @GetMapping("/{id}")
    public Optional<Account> findAccountById (@PathVariable Long id) {
       return accountService.findAccountById(id);
    }

    @PostMapping
    public void createAccount (@RequestBody Account account){
        accountService.createAccount(account);
    }

    @PutMapping
    public void updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
    }
    @DeleteMapping("/{id}")
    public void deleteAccount (@PathVariable Long id){
        accountService.deleteAccount(id);
    }

}
