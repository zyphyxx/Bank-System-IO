package com.banksystemio.banksystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String name;

    private String email;

    private Integer accountNumber;

    private String password;

    private Boolean accountStatus;

}
