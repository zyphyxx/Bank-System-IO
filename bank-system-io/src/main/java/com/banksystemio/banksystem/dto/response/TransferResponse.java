package com.banksystemio.banksystem.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferResponse {

    private Long transferId;

    private BigDecimal transferAmount;

    private Long recipientId;

}
