package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.repositories.AccountRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
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
    @Transactional
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

    @Transactional
    public void transfer(Long originID, Long recipientID,
                         BigDecimal amount, String passwordFalse) {

        Optional<Account> originAcc = accountRepository.findById(originID);
        Optional<Account> recipientAcc = accountRepository.findById(recipientID);

        if (originAcc.isPresent()) {
            String passwordTrue = originAcc.get().getPassword();
            if (passwordTrue.equals(passwordFalse)) {
                if (originAcc.get().getBalance().compareTo(amount) < 0) {
                    System.out.println("valor a baixo de 0");
                } else if (originAcc.get().getBalance().compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("voce não tem saldo");
                } else {
                    if (recipientAcc.isPresent()) {
                        originAcc.get().setBalance(originAcc.get().getBalance().subtract(amount));
                        recipientAcc.get().setBalance(recipientAcc.get().getBalance().add(amount));

                        accountRepository.save(originAcc.get());
                        accountRepository.save(recipientAcc.get());
                    }

                }
            } else {
                System.out.println("Senha invalida");
            }
        }


    }


    public BigDecimal balance(Long id) {

        Optional<Account> account = accountRepository.findById(id);
        return account.get().getBalance();
    }
}
