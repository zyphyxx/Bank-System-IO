package com.banksystemio.banksystem.exceptions.accountExceptions;

public class AccountNoSuchElementException extends RuntimeException {

    public AccountNoSuchElementException() {
        super();
    }

    public AccountNoSuchElementException(String message) {
        super(message);
    }
}
