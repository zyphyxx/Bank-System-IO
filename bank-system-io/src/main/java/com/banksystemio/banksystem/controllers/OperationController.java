package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.entities.DepositRequest;
import com.banksystemio.banksystem.dto.TransferRequest;
import com.banksystemio.banksystem.entities.WithdrawRequest;
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

    @PutMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositRequest depositRequest) {

        operationService.deposit(depositRequest.getId(),depositRequest.getAmount());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody WithdrawRequest withdrawRequest) {

        operationService.withdraw(withdrawRequest.getId(), withdrawRequest.getAmount());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> balance(@PathVariable Long id) {
        return ResponseEntity.ok().body(operationService.balance(id));
    }

    @PutMapping("transfer/{originID}")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest transferRequest) {
        operationService.transfer(
                transferRequest.getOriginID(),
                transferRequest.getRecipientID(),
                transferRequest.getAmount(),
                transferRequest.getPasswordFalse());

        return ResponseEntity.noContent().build();
    }


}
