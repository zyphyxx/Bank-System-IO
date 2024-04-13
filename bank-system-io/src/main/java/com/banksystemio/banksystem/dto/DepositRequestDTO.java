package com.banksystemio.banksystem.dto;

import com.banksystemio.banksystem.entities.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequestDTO {

    public BigDecimal amount;
    public Account id;
}
