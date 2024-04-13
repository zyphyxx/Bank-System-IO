package com.banksystemio.banksystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    Long originID;
    Long recipientID;
    BigDecimal amount;
    String passwordFalse;



}
