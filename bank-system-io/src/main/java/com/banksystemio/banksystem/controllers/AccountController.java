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
    @Operation(summary = "Metodo que retorna todas as contas", tags = "Bank-IO/Account")
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        List<Account> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok().body(AccountMapper.toList(allAccounts));
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Metodo que retorna uma conta por ID", tags = "Bank-IO/Account")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long id) {

        try {

            Optional<Account> account = accountService.findAccountById(id);
            return ResponseEntity.ok().body(AccountMapper.toAccountResponse(account.get()));

        } catch (Exception e) {
            throw new AccountNoSuchElementException();
        }

    }

    @PostMapping(value = "/create")
    @Operation(summary = "Metodo que cria uma Conta de Usuario", tags = "Bank-IO/Account")
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
    @Operation(summary = "Metodo que atualizado os dados do usuario passando uma ID", tags = "Bank-IO/Account")
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
    @Operation(summary = "Metodo que desativa a conta de usuario, só que os dados permanecem salvos", tags = "Bank-IO/Account")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    @Operation(summary = "Metodo que retorna todos os dados da conta", tags = "Bank-IO/Account")
    public Account getStausAccount(@PathVariable Long id) {
        return accountService.getStausAccount(id);
    }
}
