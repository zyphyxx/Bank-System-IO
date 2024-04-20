package com.banksystemio.banksystem.exceptions.accountExceptions;

public class AccountTransactionSystemException extends RuntimeException {

    public AccountTransactionSystemException() {
        super();
    }

    public AccountTransactionSystemException(String message) {
        super(message);
    }
}
