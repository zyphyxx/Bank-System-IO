package com.banksystemio.banksystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer accountNumber;

    private BigDecimal balance;

    private String password;

    private Boolean accountStatus;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime dateTime;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Deposit> depositRequests = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Withdraw> withdrawRequests = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transfer> transferRequests = new ArrayList<>();

    public Account () {
        this.dateTime = LocalDateTime.now();
        this.accountStatus = true;
        this.balance = BigDecimal.ZERO;
    }

}
