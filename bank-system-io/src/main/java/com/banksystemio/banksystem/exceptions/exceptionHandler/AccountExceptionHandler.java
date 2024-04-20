package com.banksystemio.banksystem.exceptions.exceptionHandler;

import com.banksystemio.banksystem.exceptions.accountExceptions.AccountMethodArgumentNotValidException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountNoSuchElementException;
import com.banksystemio.banksystem.exceptions.accountExceptions.AccountTransactionSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNoSuchElementException.class)
    private ResponseEntity<String> accountNotFoundHandler (AccountNoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi Encontrado: "+ e.getMessage());
    }

    @ExceptionHandler(AccountMethodArgumentNotValidException.class)
    private ResponseEntity<String> AccountMethodArgumentNotValidHandler (AccountMethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erro"+ e.getMessage());
    }
    @ExceptionHandler(AccountTransactionSystemException.class)
    private ResponseEntity<String> AccountTransactionSystemHandler (AccountTransactionSystemException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("falha para atualizar: "+ e.getMessage());
    }
}
