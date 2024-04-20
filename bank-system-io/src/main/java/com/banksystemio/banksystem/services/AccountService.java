package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.enums.Status;
import com.banksystemio.banksystem.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public void createAccount(Account account) {
        if ((account.getName() == null) ||
                (account.getPassword() == null ) ||
                (account.getAccountNumber() == null) ||
                (account.getEmail() == null)) {

           throw new RuntimeException("A conta não pode esta com nome, senha, e email e conta vazia");
        }
        accountRepository.save(account);

    }
    @Transactional
    public void updateAccount(Account account) {

        Optional<Account> obj = findAccountById(account.getId());

       if (obj.isPresent()){
           account.setName(account.getName());
           account.setEmail(account.getEmail());
           account.setPassword(account.getPassword());
           accountRepository.save(account);
       } else {
           throw new RuntimeException("Usuario não existe: "+ account);
       }
    }
    @Transactional
    public void deleteAccount (Long id){

        Optional<Account> obj = findAccountById(id);

        if (obj.isPresent()){
            Account account = obj.get();
            account.setAccountStatus(Status.INACTIVE);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Usuario não existe");
        }
    }

    public BigDecimal getBalance (Long id) {

        Optional<Account> account = findAccountById(id);

        return account.get().getBalance();
    }
}
