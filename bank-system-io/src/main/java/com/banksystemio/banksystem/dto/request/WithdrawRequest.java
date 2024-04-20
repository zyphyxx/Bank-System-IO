package com.banksystemio.banksystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    private Long id;

    @NotBlank
    private BigDecimal amount;

}
