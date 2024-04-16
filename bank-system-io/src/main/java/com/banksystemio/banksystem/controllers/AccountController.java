package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.AccountMapper;
import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;

import com.banksystemio.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bankio/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts(){
       return ResponseEntity.ok().body(accountService.findAllAccounts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> findAccountById (@PathVariable Long id) {
       return ResponseEntity.ok().body(accountService.findAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount (@RequestBody AccountRequest request){

        Account account = AccountMapper.toAccount(request);

        AccountResponse accountResponse = AccountMapper.toAccountResponse(account);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getId()).toUri();

        accountService.createAccount(account);
        return ResponseEntity.created(uri).body(accountResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount (@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
