package com.banksystemio.banksystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;

    private String name;

    private String email;

    private Integer accountNumber;

    private Boolean accountStatus;
}
