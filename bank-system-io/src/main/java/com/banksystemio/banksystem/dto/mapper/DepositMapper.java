package com.banksystemio.banksystem.dto.mapper;

import com.banksystemio.banksystem.dto.request.DepositRequest;
import com.banksystemio.banksystem.dto.response.DepositResponse;
import org.springframework.stereotype.Service;

@Service
public class DepositMapper {

    public static DepositResponse toResponse(DepositRequest request) {

        DepositResponse obj = new DepositResponse();

        obj.setAmount(request.getAmount());
        obj.setAccountID(request.getAccountID());

        return obj;
    }
}
