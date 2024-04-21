package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.enums.Status;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountNoSuchElementException;
import com.banksystemio.banksystem.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAccountById() {
        // Configuração do cenário
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setName("John Doe");

        // Configuração do mock: quando o método findById é chamado com o ID 1L, retorne o objeto de conta
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Execução do método a ser testado
        Optional<Account> result = accountService.findAccountById(accountId);

        // Verificação do resultado
        assertTrue(result.isPresent());
        assertEquals(account, result.get());
    }

    @Test
    public void testCreateAccount() {
        // Configuração do cenário
        Account account = new Account();
        account.setName("Jane Doe");
        account.setEmail("jane@example.com");

        // Execução do método a ser testado
        assertDoesNotThrow(() -> accountService.createAccount(account));

        // Verificação do comportamento: verifica se o método save do repository foi chamado uma vez
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testDeleteAccount() {
        // Configuração do cenário
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setAccountStatus(Status.ACTIVE);

        // Configuração do mock: quando o método findById é chamado com o ID 1L, retorne o objeto de conta
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Execução do método a ser testado
        assertDoesNotThrow(() -> accountService.deleteAccount(accountId));

        // Verificação do comportamento: verifica se o método save do repository foi chamado uma vez
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testUpdateAccount() {
        // Configuração do cenário
        Long accountId = 1L;
        Account existingAccount = new Account();
        existingAccount.setId(accountId);
        existingAccount.setName("John Doe");

        Account updatedAccount = new Account();
        updatedAccount.setId(accountId);
        updatedAccount.setName("Jane Doe");

        // Configuração do mock: quando o método findById é chamado com o ID 1L, retorne o objeto de conta existente
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        // Execução do método a ser testado
        assertDoesNotThrow(() -> accountService.updateAccount(accountId, updatedAccount));

        // Verificação do comportamento: verifica se o método save do repository foi chamado uma vez
        verify(accountRepository, times(1)).save(existingAccount);

        // Verificação do resultado: verifica se o nome da conta foi atualizado corretamente
        assertEquals("Jane Doe", existingAccount.getName());
    }

    @Test
    public void testGetStatusAccount() {
        // Configuração do cenário
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setName("John Doe");

        // Configuração do mock: quando o método findById é chamado com o ID 1L, retorne o objeto de conta
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Execução do método a ser testado
        Account result = accountService.getStausAccount(accountId);

        // Verificação do resultado
        assertNotNull(result);
        assertEquals(account, result);
    }

    @Test
    public void testGetStatusAccountNonexistent() {
        // Configuração do cenário: tentativa de buscar uma conta que não existe
        Long accountId = 1L;

        // Configuração do mock: quando o método findById é chamado com o ID 1L, retorne um Optional vazio
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Execução do método a ser testado e verificação de exceção
        assertThrows(AccountNoSuchElementException.class, () -> accountService.getStausAccount(accountId));
    }
}
