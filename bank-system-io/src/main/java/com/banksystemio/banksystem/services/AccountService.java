package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Account createAccount(Account account) {
        return accountRepository.save(account);

    }
    @Transactional
    public void updateAccount(Account account) {
        Long id = account.getId();
       Optional<Account> obj = findAccountById(id);
       if (obj.isPresent()){
           account.setName(account.getName());
           account.setEmail(account.getEmail());
           account.setPassword(account.getPassword());
           accountRepository.save(account);
       } else {
           System.out.println("Usuario não existe");
       }
    }
    @Transactional
    public void deleteAccount (Long id){
        Optional<Account> obj = findAccountById(id);
        if (obj.isPresent()){
            Account account = obj.get();
            account.setAccountStatus(false);
            accountRepository.save(account);
        } else {
            System.out.println("Usuario não existe");
        }
    }
}
