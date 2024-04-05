package entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {

    // VAR
    private String name;
    private String email;
    private Integer accountNumber;
    private BigDecimal balance;
    private String password;
    private final LocalDateTime dateTime;
    private Boolean accountStatus;

    public Account () {
        this.dateTime = LocalDateTime.now();
    }

    // GETTER AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }


}
