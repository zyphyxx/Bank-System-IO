package com.banksystemio.banksystem.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private Long transferId;

    @NotBlank
    private BigDecimal transferAmount;

    private Long recipientId;

    @NotBlank
    private String transferPassword;
}
