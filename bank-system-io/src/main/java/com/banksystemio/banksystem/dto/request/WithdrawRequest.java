package com.banksystemio.banksystem.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    private Long id;

    @NotBlank
    private BigDecimal amount;

}
