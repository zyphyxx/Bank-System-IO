package com.banksystemio.banksystem.dto.request;



import lombok.Data;

import javax.validation.constraints.NotBlank;
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
