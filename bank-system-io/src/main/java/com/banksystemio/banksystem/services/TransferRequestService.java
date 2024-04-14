package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.TransferRequest;
import com.banksystemio.banksystem.repositories.TransferRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferRequestService {

    @Autowired
    private TransferRequestRepository transferRequestRepository;
    @Autowired
    private AccountService accountService;

    @Transactional
    public void transferAmount (BigDecimal amount,Long originID,Long destinyID) {

        Optional<Account> origin = accountService.findAccountById(originID);
        Optional<Account> destiny = accountService.findAccountById(destinyID);
        TransferRequest transferRequest = new TransferRequest();

        transferRequest.setAmount(amount);
        transferRequest.setOriginName(origin.get().getName());
        transferRequest.setDestinyName(destiny.get().getName());

        transferRequestRepository.save(transferRequest);

    }

    public List<TransferRequest> transferRequestList () {
        return transferRequestRepository.findAll();
    }
}
