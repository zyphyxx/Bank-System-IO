package com.banksystemio.banksystem.dto.mapper;

import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;

public class AccountMapper {

    public static Account toAccount(AccountRequest request){

        Account account = new Account();

        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setAccountNumber(request.getAccountNumber());
        account.setPassword(request.getPassword());

        return account;
    }

    public static AccountResponse toAccountResponse(Account response){

        AccountResponse account = new AccountResponse();

        account.setName(response.getName());
        account.setEmail(response.getEmail());
        account.setAccountNumber(response.getAccountNumber());
        account.setAccountStatus(response.getAccountStatus());

        return account;
    }
}
