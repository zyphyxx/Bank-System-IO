package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.AccountMapper;
import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountMethodArgumentNotValidException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountNoSuchElementException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountTransactionSystemException;
import com.banksystemio.banksystem.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    @Operation(summary = "Retorna todas as contas",
            description = "Este método retorna todas as contas existentes no sistema.",
            tags = "Bank-IO/Account")
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<Account> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok().body(AccountMapper.toList(allAccounts));
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Retorna uma conta por ID",
            description = "Este método retorna os detalhes de uma conta específica com base no ID fornecido.",
            tags = "Bank-IO/Account")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long id) {

        try {

            Optional<Account> account = accountService.findAccountById(id);
            return ResponseEntity.ok().body(AccountMapper.toAccountResponse(account.get()));

        } catch (Exception e) {
            throw new AccountNoSuchElementException();
        }

    }

    @PostMapping(value = "/create")
    @Operation(summary = "Cria uma conta de usuário",
            description = "Este método cria uma nova conta de usuário com base nos detalhes fornecidos.",
            tags = "Bank-IO/Account")
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
    @Operation(summary = "Atualiza os dados do usuário por ID",
            description = "Este método atualiza os dados de uma conta de usuário existente com base no ID fornecido.",
            tags = "Bank-IO/Account")
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
    @Operation(summary = "Desativa a conta de usuário",
            description = "Este método desativa a conta de um usuário, mantendo os dados salvos no sistema.",
            tags = "Bank-IO/Account")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    @Operation(summary = "Retorna todos os dados da conta",
            description = "Este método retorna todos os detalhes e informações associadas a uma conta específica.",
            tags = "Bank-IO/Account")
    public Account getStausAccount(@PathVariable Long id) {
        return accountService.getStausAccount(id);
    }
}
