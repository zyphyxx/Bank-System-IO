package com.banksystemio.banksystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class TransferRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate transferDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime transferTime;

    private String originName;

    private Long originID;

    private String destinyName;

    private Long recipientID;

    private String passwordFalse;

    @ManyToOne
    @JsonIgnore
    private Account account;


    public TransferRequest () {
        transferDate = LocalDate.now();
        transferTime = LocalTime.now();

    }
}
