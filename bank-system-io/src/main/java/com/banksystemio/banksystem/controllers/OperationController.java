package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bankio")
public class OperationController {

    @Autowired
    private OperationService operationServiceService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody BigDecimal amount) {
        operationServiceService.deposit(amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody BigDecimal amount) {
        operationServiceService.withdraw(amount);
    }






}
