package com.banksystemio.banksystem.dto.mapper;

import com.banksystemio.banksystem.dto.request.TransferRequest;
import com.banksystemio.banksystem.dto.response.TransferResponse;

public class TransferMapper {

    public static TransferResponse toResponse(TransferRequest request) {

            TransferResponse response = new TransferResponse();

            response.setTransferId(request.getTransferId());
            response.setTransferAmount(request.getTransferAmount());
            response.setRecipientId(request.getRecipientId());

            return response;
    }
}
