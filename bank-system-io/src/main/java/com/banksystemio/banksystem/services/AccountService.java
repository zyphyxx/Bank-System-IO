package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    Account account;

    public AccountService() {

        account = new Account();
        account.setBalance(BigDecimal.ZERO);
    }

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


    public void createAccount(String name, String email, String password, Integer accountNumber) {
        account.setName(name);
        account.setEmail(email);
        account.setPassword(password);
        account.setAccountNumber(accountNumber);
        account.setAccountStatus(true);
        account.setBalance(BigDecimal.ZERO);

        accountRepository.save(account);
    }

    public BigDecimal balance() {
        return account.getBalance();
    }
}
