package com.banksystemio.banksystem.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    private Long id;
    private BigDecimal amount;

}
