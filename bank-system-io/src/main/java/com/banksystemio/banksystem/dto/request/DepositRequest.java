package com.banksystemio.banksystem.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class DepositRequest {


    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private Long accountID;

}
