package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.enums.Status;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountNoSuchElementException;
import com.banksystemio.banksystem.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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

        account.setAccountStatus(Status.ACTIVE);
        accountRepository.save(account);

    }

    @Transactional
    public void updateAccount(Long id, Account account) {

        Optional<Account> optionalAccount = findAccountById(id);

        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            existingAccount.setName(account.getName());
            existingAccount.setEmail(account.getEmail());
            existingAccount.setAccountNumber(account.getAccountNumber());
            existingAccount.setPassword(account.getPassword());

            accountRepository.save(existingAccount);
        } else {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + account.getId());
        }
    }


    @Transactional
    public void deleteAccount(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        Optional<Account> obj = findAccountById(id);

        if (obj.isPresent()) {

            Account account = obj.get();

            account.setAccountStatus(Status.INACTIVE);
            updateAccount(id, obj.get());
            accountRepository.save(account);

        } else {
            throw new AccountNoSuchElementException();
        }
    }

    public Account getStausAccount(Long id) {
        try {
            Optional<Account> account = findAccountById(id);
            return account.get();

        } catch (Exception e){
            throw new AccountNoSuchElementException();
        }

    }
}
