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
       account.setBalance(new BigDecimal(0));
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
        if (amount.compareTo(BigDecimal.ZERO) < 0){
            return;
        }
        account.setBalance(balance.add(amount));
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0){
            return;
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }
            account.setBalance(balance.subtract(amount));

    }

    @Override
    public void transfer(BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0){
            return;
        } else if (amount.compareTo(BigDecimal.ZERO) < 0){
            return;
        }
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

    @Override
    public BigDecimal balance() {
        return account.getBalance();
    }
}
