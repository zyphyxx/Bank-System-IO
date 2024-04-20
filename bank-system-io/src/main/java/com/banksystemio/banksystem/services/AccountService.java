package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.enums.Status;
import com.banksystemio.banksystem.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
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
                (account.getPassword() == null) ||
                (account.getAccountNumber() == null) ||
                (account.getEmail() == null)) {

            throw new RuntimeException("A conta não pode esta com nome, senha, e email e conta vazia");
        }

        account.setAccountStatus(Status.ACTIVE);

        accountRepository.save(account);

    }

    @Transactional
    public void updateAccount(Long id,Account account) {

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
            updateAccount(id,obj.get());
            accountRepository.save(account);

        } else {
            throw new RuntimeException("Usuário não existe");
        }
    }

    public Account getStausAccount(Long id) {

        Optional<Account> account = findAccountById(id);

        return account.get();
    }
}
