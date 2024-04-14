package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.WithdrawRequest;
import com.banksystemio.banksystem.repositories.WithdrawRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawRequestService {

    @Autowired
    private WithdrawRequestRepository withdrawRequestRepository;
    @Autowired
    private AccountService accountService;

    public void withdrawAmount (BigDecimal amount,Long id) {
        WithdrawRequest withdrawRequest = new WithdrawRequest();

        Optional<Account> account = accountService.findAccountById(id);

        withdrawRequest.setAmount(amount);
        withdrawRequest.setAccount(account.get());
        withdrawRequestRepository.save(withdrawRequest);

    }
}
