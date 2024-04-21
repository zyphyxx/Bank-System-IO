package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Transfer;
import com.banksystemio.banksystem.repositories.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferAmount() {
        // Configuração do cenário
        Long transferId = 1L;
        BigDecimal transferAmount = BigDecimal.valueOf(50);
        Long recipientId = 2L;
        String transferPassword = "password";

        Account remetente = new Account();
        remetente.setId(transferId);
        remetente.setBalance(BigDecimal.valueOf(100));
        remetente.setPassword(transferPassword);

        Account destinatario = new Account();
        destinatario.setId(recipientId);
        destinatario.setBalance(BigDecimal.valueOf(200));

        when(accountService.findAccountById(transferId)).thenReturn(Optional.of(remetente));
        when(accountService.findAccountById(recipientId)).thenReturn(Optional.of(destinatario));

        // Execução do método a ser testado
        transferService.transferAmount(transferId, transferAmount, recipientId, transferPassword);

        // Verificação do resultado
        assertEquals(BigDecimal.valueOf(50), remetente.getBalance());
        assertEquals(BigDecimal.valueOf(250), destinatario.getBalance());

        // Verificação do comportamento
        verify(accountService, times(1)).updateAccount(transferId, remetente);
        verify(accountService, times(1)).updateAccount(recipientId, destinatario);
        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    public void testTransferAmountInsufficientBalance() {
        // Configuração do cenário
        Long transferId = 1L;
        BigDecimal transferAmount = BigDecimal.valueOf(150);
        Long recipientId = 2L;
        String transferPassword = "password";

        Account remetente = new Account();
        remetente.setBalance(BigDecimal.valueOf(100));
        remetente.setPassword(transferPassword);

        when(accountService.findAccountById(transferId)).thenReturn(Optional.of(remetente));

        // Execução do método a ser testado e verificação da exceção lançada
        assertThrows(RuntimeException.class, () -> transferService.transferAmount(transferId, transferAmount, recipientId, transferPassword));

        // Verificação do comportamento
        verify(accountService, never()).updateAccount(anyLong(), any(Account.class));
        verify(transferRepository, never()).save(any(Transfer.class));
    }

    // Outros testes podem ser adicionados para verificar o comportamento em outras condições, como senhas inválidas, valores negativos, etc.
}
