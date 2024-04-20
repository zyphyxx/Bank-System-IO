package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.AccountMapper;
import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;

import com.banksystemio.banksystem.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("bankio/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping("/find/all")
    public ResponseEntity<List<AccountResponse>> findAllAccounts(){

        List<AccountResponse> tolist = AccountMapper.toList(accountService.findAllAccounts());

        return ResponseEntity.ok().body(tolist);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<AccountResponse> findAccountById (@PathVariable Long id) {

            accountService.findAccountById(id);

            AccountResponse accountResponse = AccountMapper.toAccountResponse(accountService.findAccountById(id).get());

            return ResponseEntity.ok().body(accountResponse);

    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount (@Valid @RequestBody AccountRequest request){

        Account account = AccountMapper.toAccount(request);
        accountService.createAccount(account);

        AccountResponse accountResponse = AccountMapper.toAccountResponse(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.created(location).body(accountResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@Valid @PathVariable Long id, @RequestBody AccountRequest request) {

        Account account = AccountMapper.toAccount(request);
        AccountResponse accountResponse = AccountMapper.toAccountResponse(account);

        accountService.updateAccount(id,account);

        return ResponseEntity.ok().body(accountResponse);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount (@PathVariable Long id){

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    public Account getStausAccount(@PathVariable Long id) {
       return accountService.getStausAccount(id);
    }
}
