package com.banksystemio.banksystem.dto.response;

import com.banksystemio.banksystem.entities.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DepositResponse {

    private Long id;

    private BigDecimal amount;

    private Long accountID;

    private LocalDate depositDate;

    private LocalTime depositTime;
}
