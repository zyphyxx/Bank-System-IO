package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Withdraw;
import com.banksystemio.banksystem.repositories.WithdrawRepository;
import com.banksystemio.banksystem.services.AccountService;
import com.banksystemio.banksystem.services.WithdrawService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WithdrawServiceTest {

    // Usamos a anotação @InjectMocks para injetar mocks nas dependências da classe que estamos testando
    @InjectMocks
    private WithdrawService withdrawService;

    // Usamos a anotação @Mock para criar mocks para as dependências da classe que estamos testando
    @Mock
    private WithdrawRepository withdrawRepository;

    @Mock
    private AccountService accountService;

    // Usamos a anotação @BeforeEach para garantir que o método setup() seja executado antes de cada teste
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se uma exceção é lançada quando o saldo é insuficiente para o saque
    @Test
    public void testWithdrawAmountWithInsufficientBalance() {
        // Configuração do cenário: conta com saldo insuficiente para o saque
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(100));

        // Configuração do mock: quando o método findAccountById é chamado com o ID 1L, retorne um Optional contendo a conta
        when(accountService.findAccountById(1L)).thenReturn(Optional.of(account));

        // Execução do método a ser testado: tentativa de saque de uma quantia maior do que o saldo
        BigDecimal amount = BigDecimal.valueOf(150);
        // Verifica se uma exceção é lançada quando o saldo é insuficiente para o saque
        assertThrows(RuntimeException.class, () -> withdrawService.withdrawAmount(amount, 1L));

        // Verifica se os métodos save e updateAccount não foram chamados
        verify(withdrawRepository, never()).save(any(Withdraw.class));
        verify(accountService, never()).updateAccount(anyLong(), any(Account.class));
    }

    // Teste para verificar se uma exceção é lançada quando a quantia do saque é negativa
    @Test
    public void testWithdrawAmountWithNegativeValue() {
        // Configuração do cenário: tentativa de saque de uma quantia negativa
        BigDecimal amount = BigDecimal.valueOf(-50);
        // Verifica se uma exceção é lançada quando a quantia é negativa
        assertThrows(RuntimeException.class, () -> withdrawService.withdrawAmount(amount, 1L));
    }

    // Teste para verificar se o saque é bem-sucedido
    @Test
    public void testWithdrawAmount() {
        // Configuração do cenário: saque bem-sucedido
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(100));

        // Configuração do mock: quando o método findAccountById é chamado com o ID 1L, retorne um Optional contendo a conta
        when(accountService.findAccountById(1L)).thenReturn(Optional.of(account));

        // Execução do método a ser testado: saque de uma quantia válida
        BigDecimal amount = BigDecimal.valueOf(50);
        withdrawService.withdrawAmount(amount, 1L);

        // Verificação do resultado: verifica se o saldo da conta foi atualizado corretamente
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
        // Verificação do comportamento: verifica se o método updateAccount do AccountService foi chamado
        verify(accountService, times(1)).updateAccount(1L, account);
        // Verificação do comportamento: verifica se o método save do WithdrawRepository foi chamado
        verify(withdrawRepository, times(1)).save(any(Withdraw.class));
    }
}
