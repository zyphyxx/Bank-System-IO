package com.banksystemio.banksystem.dto.response;

import com.banksystemio.banksystem.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Status accountStatus;
}
