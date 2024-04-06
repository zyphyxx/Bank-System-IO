package repositories;

import entities.Account;

import java.math.BigDecimal;

public interface BankAccountOperations {

    void deposit (BigDecimal amount);
    void withdraw (BigDecimal amount);
    void transfer (BigDecimal amount);
    void createAccount (String name, String email, String password,Integer accountNumber);
    BigDecimal balance ();

}
