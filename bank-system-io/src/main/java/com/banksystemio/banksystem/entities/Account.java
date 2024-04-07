package com.banksystemio.banksystem.entities;

import com.banksystemio.banksystem.utils.DateTimeUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.BeanProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Account {

    // VAR
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer accountNumber;

    private BigDecimal balance;

    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime dateTime;

    private Boolean accountStatus;

    public Account () {
        this.dateTime = LocalDateTime.now();
        this.accountStatus = true;
        this.balance = BigDecimal.ZERO;

    }
    public Account (String name, String email,String password) {
        this.dateTime = LocalDateTime.now();
        this.accountStatus = true;
        this.balance = BigDecimal.ZERO;
    }


}