package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.DepositMapper;
import com.banksystemio.banksystem.dto.request.DepositRequest;
import com.banksystemio.banksystem.dto.response.DepositResponse;
import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Deposit;
import com.banksystemio.banksystem.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("bankio/operation")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> depositAmount (@RequestBody DepositRequest request){

        DepositResponse response = DepositMapper.toResponse(request);

        depositService.depositAmount(request.getAmount(),request.getAccountID());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/deposit/all")
    public List<Deposit> findAllDeposits () {
        return depositService.findAllDeposits();
    }
}
