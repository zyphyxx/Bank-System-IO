package com.banksystemio.banksystem.dto.mapper;

import com.banksystemio.banksystem.dto.request.AccountRequest;
import com.banksystemio.banksystem.dto.response.AccountResponse;
import com.banksystemio.banksystem.entities.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

        account.setId(response.getId());
        account.setName(response.getName());
        account.setEmail(response.getEmail());
        account.setAccountNumber(response.getAccountNumber());
        account.setAccountStatus(response.getAccountStatus());

        return account;
    }

    // Método estático para converter uma lista de objetos Account em uma lista de objetos AccountResponse
    public static List<AccountResponse> toList(List<Account> accounts){

        // Cria uma nova lista para armazenar os objetos AccountResponse convertidos
        List<AccountResponse> accountResponseList = new ArrayList<>();

        // Itera sobre cada objeto Account na lista fornecida como parâmetro
        for (Account account : accounts) {
            // Converte o objeto Account atual para um objeto AccountResponse
            AccountResponse accountResponse = toAccountResponse(account);
            // Adiciona o objeto AccountResponse convertido à lista de respostas
            accountResponseList.add(accountResponse);
        }

        // Retorna a lista de AccountResponse contendo todas as conversões
        return accountResponseList;
    }

}
