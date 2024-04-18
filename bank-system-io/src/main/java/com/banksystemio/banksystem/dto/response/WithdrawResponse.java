package com.banksystemio.banksystem.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawResponse {

    private Long id;
    private BigDecimal amount;
}
