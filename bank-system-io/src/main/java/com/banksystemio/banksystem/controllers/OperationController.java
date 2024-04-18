package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.entities.Deposit;
import com.banksystemio.banksystem.entities.Transfer;

import com.banksystemio.banksystem.entities.Withdraw;

import com.banksystemio.banksystem.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bankio/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    /*
    @PutMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody Deposit depositRequest) {

        operationService.deposit(depositRequest.getId(), depositRequest.getAmount());

        return ResponseEntity.noContent().build();
    }

     */

    @PutMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody Withdraw withdrawRequest) {

        operationService.withdraw(withdrawRequest.getId(), withdrawRequest.getAmount());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> balance(@PathVariable Long id) {
        return ResponseEntity.ok().body(operationService.balance(id));
    }

    @PutMapping("transfer")
    public ResponseEntity<Void> transfer(@RequestBody Transfer transferRequest) {

        operationService.transfer(
                transferRequest.getOriginID(),
                transferRequest.getRecipientID(),
                transferRequest.getAmount(),
                transferRequest.getPasswordFalse());

        return ResponseEntity.noContent().build();
    }


}