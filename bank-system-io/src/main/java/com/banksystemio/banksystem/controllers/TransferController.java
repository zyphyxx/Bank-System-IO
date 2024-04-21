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
    @Operation(summary = "Realiza uma transferência",
            description = "Este método é usado para fazer transferências entre contas bancárias. Requer o ID da conta de origem, o valor a transferir, o ID da conta de destino e a senha do usuário.",
            tags = "Bank-IO/Operation/Transfer")
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
