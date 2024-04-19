package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Withdraw;
import com.banksystemio.banksystem.repositories.WithdrawRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRequestRepository;
    @Autowired
    private AccountService accountService;

    @Transactional
    public void withdrawAmount(BigDecimal amount, Long id) {

        Withdraw withdrawRequest = new Withdraw();

        Optional<Account> account = accountService.findAccountById(id);
        BigDecimal balance = account.get().getBalance();

        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("voce não tem saldo R$" + balance);
        } else if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("o valor não pode ser negativo "+ amount);
        } else {

            try {
                account.get().setBalance(balance.subtract(amount));
                accountService.updateAccount(account.get());

                withdrawRequest.setAmount(amount);
                withdrawRequest.setAccount(account.get());
                withdrawRequestRepository.save(withdrawRequest);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }



    }

    public List<Withdraw> findAllWithdraws () {
      return withdrawRequestRepository.findAll();
    }
}
