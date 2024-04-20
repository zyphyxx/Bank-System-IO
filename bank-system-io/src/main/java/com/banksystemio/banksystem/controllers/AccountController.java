package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.AccountMapper;
import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountMethodArgumentNotValidException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountNoSuchElementException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountTransactionSystemException;
import com.banksystemio.banksystem.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("bankio/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping("/find/all")
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<Account> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok().body(AccountMapper.toList(allAccounts));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long id) {

        try {

            Optional<Account> account = accountService.findAccountById(id);
            return ResponseEntity.ok().body(AccountMapper.toAccountResponse(account.get()));

        } catch (Exception e) {
            throw new AccountNoSuchElementException();
        }

    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        try {

            Account account = AccountMapper.toAccount(request);
            accountService.createAccount(account);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(account.getId())
                    .toUri();

            return ResponseEntity.created(location).body(AccountMapper.toAccountResponse(account));

        } catch (Exception e) {
            throw new AccountMethodArgumentNotValidException();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@Valid @PathVariable Long id, @RequestBody AccountRequest request) {

        try {

            Account account = AccountMapper.toAccount(request);
            accountService.updateAccount(id, account);
            return ResponseEntity.ok().body(AccountMapper.toAccountResponse(account));

        } catch (Exception e){
            throw new AccountTransactionSystemException();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    public Account getStausAccount(@PathVariable Long id) {
        return accountService.getStausAccount(id);
    }
}
