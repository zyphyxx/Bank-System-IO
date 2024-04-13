package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.DepositRequestDTO;
import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.services.DepositRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class DepositRequestController {

    @Autowired
    private DepositRequestService depositRequestService;

    @GetMapping
    public void depositAmount (DepositRequestDTO depositRequestDTO){
        depositRequestService.depositAmount(depositRequestDTO.getAmount(),depositRequestDTO.id);
    }
}
