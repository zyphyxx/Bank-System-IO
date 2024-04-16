package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Transfer;
import com.banksystemio.banksystem.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRequestRepository;
    @Autowired
    private AccountService accountService;

    @Transactional
    public void transferAmount (BigDecimal amount,Long originID,Long destinyID) {

        Optional<Account> origin = accountService.findAccountById(originID);
        Optional<Account> destiny = accountService.findAccountById(destinyID);
        Transfer transferRequest = new Transfer();

        transferRequest.setAmount(amount);
        transferRequest.setOriginName(origin.get().getName());
        transferRequest.setDestinyName(destiny.get().getName());
        transferRequest.setRecipientID(destinyID);
        transferRequest.setOriginID(originID);
        transferRequest.setAccount(origin.get());



        transferRequestRepository.save(transferRequest);

    }

}
