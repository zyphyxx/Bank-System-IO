package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.controllers.AccountController;
import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllAccounts() {
        // Configuração do mock do serviço para retornar uma lista de contas
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        when(accountService.findAllAccounts()).thenReturn(accounts);

        // Chamada do método no controlador
        ResponseEntity<List<AccountResponse>> responseEntity = accountController.findAllAccounts();

        // Verificação se o status da resposta é OK (200) e se a lista de contas está presente
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accounts.size(), responseEntity.getBody().size());
    }

    @Test
    public void testFindAccountById() {
        // Configuração do mock do serviço para retornar uma conta
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        when(accountService.findAccountById(accountId)).thenReturn(Optional.of(account));

        // Chamada do método no controlador
        ResponseEntity<AccountResponse> responseEntity = accountController.findAccountById(accountId);

        // Verificação se o status da resposta é OK (200) e se a conta está presente
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountId, responseEntity.getBody().getId());
    }

    // Outros testes para os métodos createAccount, updateAccount, deleteAccount e getStausAccount podem ser adicionados de maneira semelhante.
}
