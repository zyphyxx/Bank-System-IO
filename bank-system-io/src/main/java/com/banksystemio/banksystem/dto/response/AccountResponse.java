package com.banksystemio.banksystem.dto.response;

import com.banksystemio.banksystem.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




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
