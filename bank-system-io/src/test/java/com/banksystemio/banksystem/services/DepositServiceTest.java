package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Deposit;
import com.banksystemio.banksystem.repositories.DepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

    @InjectMocks
    private DepositService depositService;

    @Mock
    DepositRepository depositRepository;

    @BeforeEach
    public void setUp () {
        Deposit deposit = new Deposit();
    }



}
