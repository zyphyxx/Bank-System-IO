package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.WithdrawMapper;
import com.banksystemio.banksystem.dto.request.WithdrawRequest;
import com.banksystemio.banksystem.dto.response.WithdrawResponse;

import com.banksystemio.banksystem.entities.Withdraw;
import com.banksystemio.banksystem.services.WithdrawService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bankio/operation")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @PutMapping("/withdraw")
    @Operation(summary = "Realiza um saque",
            description = "Este método é usado para fazer saques de uma conta bancária. Requer o ID da conta e o valor a ser sacado.",
            tags = "Bank-IO/Operation/Withdraw")
    public ResponseEntity<WithdrawResponse> withdrawAmount(@RequestBody WithdrawRequest request) {
        WithdrawResponse response = WithdrawMapper.toResponse(request);

        withdrawService.withdrawAmount(response.getAmount(),response.getId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/withdraw/all")
    @Operation(summary = "Metodo usado para retornar todas transferencias", tags = "Bank-IO/Operation/Withdraw")
    public List<Withdraw> findAllWithdraws () {
        return withdrawService.findAllWithdraws();
    }
}
