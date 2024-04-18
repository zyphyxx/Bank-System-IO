package com.banksystemio.banksystem.dto.mapper;

import com.banksystemio.banksystem.dto.request.WithdrawRequest;
import com.banksystemio.banksystem.dto.response.WithdrawResponse;

public class WithdrawMapper {


    public static WithdrawResponse toResponse (WithdrawRequest request){
        WithdrawResponse response = new WithdrawResponse();

        response.setAmount(request.getAmount());
        response.setId(request.getId());

        return response;
    }
}
