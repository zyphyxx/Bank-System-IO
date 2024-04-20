package com.banksystemio.banksystem.services;

import com.banksystemio.banksystem.entities.Account;
import com.banksystemio.banksystem.entities.Transfer;
import com.banksystemio.banksystem.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private AccountService accountService;

    @Transactional
    public void transferAmount(Long transferId,
                               BigDecimal transferAmount,
                               Long recipientId,
                               String transferPassword) {

        Optional<Account> remetente = accountService.findAccountById(transferId);
        Optional<Account> destinatario = accountService.findAccountById(recipientId);

        BigDecimal remetenteBalance = remetente.get().getBalance();
        remetente.get().setBalance(remetenteBalance.subtract(transferAmount));
        String password = remetente.get().getPassword();

        BigDecimal destinatarioBalance = destinatario.get().getBalance();
        destinatario.get().setBalance(destinatarioBalance.add(transferAmount));

        if (remetenteBalance.compareTo(transferAmount) < 0) {
            throw new RuntimeException("O usuario não tem saldo R$:" + remetenteBalance);

        } else if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("O valor de deposito não pode ser negativo R$:" + transferAmount);

        } else if (!Objects.equals(transferPassword, password)) {
            throw new RuntimeException("senha invalida");

        } else if (transferPassword.equals(password)) {

            try {

                accountService.updateAccount(transferId,remetente.get());
                accountService.updateAccount(recipientId,destinatario.get());

                Transfer transfer = new Transfer();
                transfer.setTransferId(remetente.get().getId());
                transfer.setTransferAmount(transferAmount);
                transfer.setRecipientId(destinatario.get().getId());
                transferRepository.save(transfer);


            } catch (RuntimeException e) {
                throw new RuntimeException(e.getCause());
            }

        }


    }

    public List<Transfer> findAllTransfer() {
        return transferRepository.findAll();
    }

}
