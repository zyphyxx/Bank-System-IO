package com.banksystemio.banksystem.dto.request;

import com.banksystemio.banksystem.entities.Account;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {


    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private Long accountID;

}
