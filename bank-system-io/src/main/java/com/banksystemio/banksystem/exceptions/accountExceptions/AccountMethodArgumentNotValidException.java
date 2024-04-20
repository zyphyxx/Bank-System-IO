package com.banksystemio.banksystem.exceptions.accountExceptions;

public class AccountMethodArgumentNotValidException extends RuntimeException {

    public AccountMethodArgumentNotValidException() {
        super();
    }

    public AccountMethodArgumentNotValidException(String message) {
        super(message);
    }
}
