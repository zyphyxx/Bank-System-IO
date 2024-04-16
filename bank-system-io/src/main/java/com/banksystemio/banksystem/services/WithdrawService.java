package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Withdraw;
import com.banksystemio.banksystem.repositories.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRequestRepository;
    @Autowired
    private AccountService accountService;

    public void withdrawAmount (BigDecimal amount,Long id) {
        Withdraw withdrawRequest = new Withdraw();

        Optional<Account> account = accountService.findAccountById(id);

        withdrawRequest.setAmount(amount);
        withdrawRequest.setAccount(account.get());
        withdrawRequestRepository.save(withdrawRequest);

    }
}
