package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    public void deposit(Long id, BigDecimal amount) {
        Optional<Account> acc = accountService.findAccountById(id);

        if (acc.isPresent()) {
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                return;
            }
            acc.get().setBalance(acc.get().getBalance().add(amount));
            accountRepository.save(acc.get());
        } else {
            System.out.println("Usuario não existe");
        }
    }

    public void withdraw(Long id, BigDecimal amount) {

        Optional<Account> acc = accountService.findAccountById(id);

        if (acc.isPresent()) {
            BigDecimal balance = acc.get().getBalance();

            if (balance.compareTo(amount) < 0) {
                acc.get().getBalance();
            } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
                acc.get().getBalance();
            } else {

                acc.get().setBalance(balance.subtract(amount));
                accountRepository.save(acc.get());
            }

        } else {
            System.out.println("Usuario não existe");
        }


    }


    public void transfer(BigDecimal amount) {

    }


    public BigDecimal balance(Long id) {

        Optional<Account> account = accountRepository.findById(id);
        return account.get().getBalance();
    }
}
