package com.banksystemio.banksystem.dto.request;

import com.banksystemio.banksystem.entities.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {

    private BigDecimal amount;

    private Long accountID;

}
