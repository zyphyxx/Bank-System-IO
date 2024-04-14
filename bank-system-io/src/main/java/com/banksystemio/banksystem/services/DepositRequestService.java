package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.DepositRequest;
import com.banksystemio.banksystem.repositories.DepositRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositRequestService {

    @Autowired
    private DepositRequestRepository depositRequestRepository;



    public List<DepositRequest> findAllDeposits () {
        return depositRequestRepository.findAll();
    }

    @Transactional
    public void depositAmount (BigDecimal amount,Account account){
       DepositRequest depositRequest = new DepositRequest();
       depositRequest.setAmount(amount);
       depositRequest.setAccount(account);
       depositRequestRepository.save(depositRequest);

    }
}
