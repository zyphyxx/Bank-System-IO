package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperationService {

    @Autowired
    private AccountRepository accountRepository;


    private Account account;

    public void deposit(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }
        account.setBalance(balance.add(amount));

        accountRepository.save(account);

    }

    public void withdraw(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0) {
            return;
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }
        account.setBalance(balance.subtract(amount));

        accountRepository.save(account);
    }


    public void transfer(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0) {
            return;
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }
        account.setBalance(balance.subtract(amount));

        accountRepository.save(account);
    }


    public BigDecimal balance() {
        return account.getBalance();
    }
}
