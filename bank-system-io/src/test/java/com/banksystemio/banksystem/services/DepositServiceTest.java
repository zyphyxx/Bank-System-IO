package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Deposit;
import com.banksystemio.banksystem.repositories.DepositRepository;
import com.banksystemio.banksystem.services.DepositService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

// Usamos a anotação @RunWith para usar o MockitoJUnitRunner para executar os testes
@RunWith(MockitoJUnitRunner.class)
public class DepositServiceTest {

    // Usamos a anotação @InjectMocks para injetar mocks nas dependências da classe que estamos testando
    @InjectMocks
    private DepositService depositService;

    // Usamos a anotação @Mock para criar mocks para as dependências da classe que estamos testando
    @Mock
    private DepositRepository depositRepository;

    @Mock
    private AccountService accountService;

    // Usamos a anotação @Before para garantir que o método setUp() seja executado antes de cada teste
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se o método depositAmount funciona corretamente
    @Test
    public void testDepositAmount() {
        // Configuração do cenário
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(100));

        // Configuração do mock: quando o método findAccountById é chamado com o ID 1L, retorne um Optional contendo a conta
        when(accountService.findAccountById(1L)).thenReturn(Optional.of(account));

        // Execução do método a ser testado
        BigDecimal amount = BigDecimal.valueOf(50);
        depositService.depositAmount(amount, 1L);

        // Verificação do resultado: verifica se o saldo da conta foi atualizado corretamente
        assertEquals(BigDecimal.valueOf(150), account.getBalance());
        // Verificação do comportamento: verifica se o método save do DepositRepository foi chamado uma vez
        verify(depositRepository, times(1)).save(any(Deposit.class));
    }

    // Teste para verificar se uma exceção é lançada quando a quantia do depósito é negativa
    @Test
    public void testDepositAmountWithNegativeValue() {
        BigDecimal amount = BigDecimal.valueOf(-50);
        // Verificação do comportamento: verifica se uma exceção é lançada quando a quantia é negativa
        assertThrows(RuntimeException.class, () -> depositService.depositAmount(amount, 1L));
    }

    // Teste para verificar se uma exceção é lançada quando a quantia do depósito é nula
    @Test
    public void testDepositAmountWithNullValue() {
        BigDecimal amount = null;
        // Verificação do comportamento: verifica se uma exceção é lançada quando a quantia é nula
        assertThrows(RuntimeException.class, () -> depositService.depositAmount(amount, 1L));
    }

    // Teste para verificar se uma exceção é lançada quando a conta não existe
    @Test
    public void testDepositAmountWithNonexistentAccount() {
        // Configuração do mock: quando o método findAccountById é chamado com o ID 1L, retorne um Optional vazio
        when(accountService.findAccountById(1L)).thenReturn(Optional.empty());

        BigDecimal amount = BigDecimal.valueOf(50);
        // Verificação do comportamento: verifica se uma exceção é lançada quando a conta não existe
        assertThrows(RuntimeException.class, () -> depositService.depositAmount(amount, 1L));
    }
}
