package services;

import entities.Account;
import repositories.BankAccountOperations;

import java.math.BigDecimal;

public class BankAccountOperationsService implements BankAccountOperations {

    // VAR
    private static BankAccountOperationsService instance;
    private Account account;
    // CONSTRUTOR PRIVADO
    private BankAccountOperationsService(){
       account = new Account();
    }
    // METODO PARA INSTANCIAR
    public static BankAccountOperationsService getInstance(){
        if (instance == null){
            instance = new BankAccountOperationsService();
        }
        return instance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(amount));
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.subtract(amount));
    }

    @Override
    public void transfer(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.subtract(amount));
    }

    @Override
    public void createAccount(String name, String email, String password, Integer accountNumber) {
        account.setName(name);
        account.setEmail(email);
        account.setPassword(password);
        account.setAccountNumber(accountNumber);
        account.setAccountStatus(true);
        account.setBalance(BigDecimal.ZERO);
    }
}
