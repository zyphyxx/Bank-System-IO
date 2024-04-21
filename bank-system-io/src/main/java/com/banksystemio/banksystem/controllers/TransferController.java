package com.banksystemio.banksystem.controllers;

import com.banksystemio.banksystem.dto.mapper.TransferMapper;
import com.banksystemio.banksystem.dto.request.TransferRequest;
import com.banksystemio.banksystem.dto.response.TransferResponse;
import com.banksystemio.banksystem.entities.Transfer;
import com.banksystemio.banksystem.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankio/operation")

public class TransferController {

    @Autowired
    private TransferService transferService;

    @PutMapping("/transfer")
    @Operation(summary = "Metodo usado para fazer transferencias, Passando o ID da conta, o valor a transferir, ID de quem vai receber, e a senha do usuario", tags = "Bank-IO/Operation/Transfer")
    public ResponseEntity<TransferResponse> transferAmount(@RequestBody TransferRequest request) {

        TransferResponse response = TransferMapper.toResponse(request);

        transferService.transferAmount(
                response.getTransferId(),
                response.getTransferAmount(),
                response.getRecipientId(),
                request.getTransferPassword());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transfer/all")
    @Operation(summary = "Metodo que retorna todas as transferencias", tags = "Bank-IO/Operation/Transfer")
    public List<Transfer> findAllTransfer() {
        return transferService.findAllTransfer();
    }
}
