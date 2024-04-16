package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Deposit;
import com.banksystemio.banksystem.repositories.DepositRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRequestRepository;



    public List<Deposit> findAllDeposits () {
        return depositRequestRepository.findAll();
    }

    @Transactional
    public void depositAmount (BigDecimal amount,Account account){
       Deposit depositRequest = new Deposit();
       depositRequest.setAmount(amount);
       depositRequest.setAccount(account);
       depositRequestRepository.save(depositRequest);

    }
}
