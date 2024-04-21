package com.banksystemio.banksystem.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.banksystemio.banksystem.controllers.WithdrawController;
import com.banksystemio.banksystem.dto.request.WithdrawRequest;
import com.banksystemio.banksystem.dto.response.WithdrawResponse;
import com.banksystemio.banksystem.entities.Withdraw;
import com.banksystemio.banksystem.services.WithdrawService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WithdrawControllerTest {

    @InjectMocks
    private WithdrawController withdrawController;

    @Mock
    private WithdrawService withdrawService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWithdrawAmount() {
        WithdrawRequest request = new WithdrawRequest();
        request.setId(1L);
        request.setAmount(BigDecimal.valueOf(50));

        // Configurando o comportamento do método withdrawAmount para não fazer nada (já que retorna void)
        doNothing().when(withdrawService).withdrawAmount(request.getAmount(), request.getId());

        ResponseEntity<WithdrawResponse> result = withdrawController.withdrawAmount(request);

        // Verifica se o ResponseEntity não é null
        assertNotNull(result);

        // Verifica se o método withdrawAmount do WithdrawService foi chamado com os argumentos corretos
        verify(withdrawService, times(1)).withdrawAmount(request.getAmount(), request.getId());
    }


    @Test
    public void testFindAllWithdraws() {
        List<Withdraw> withdraws = new ArrayList<>();
        withdraws.add(new Withdraw());
        withdraws.add(new Withdraw());

        when(withdrawService.findAllWithdraws()).thenReturn(withdraws);

        List<Withdraw> result = withdrawController.findAllWithdraws();

        assertEquals(withdraws.size(), result.size());
        verify(withdrawService, times(1)).findAllWithdraws();
    }
}
