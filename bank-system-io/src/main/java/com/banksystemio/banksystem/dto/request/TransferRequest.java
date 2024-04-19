package com.banksystemio.banksystem.dto.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private Long transferId;

    private BigDecimal transferAmount;

    private Long recipientId;

    private String transferPassword;
}
