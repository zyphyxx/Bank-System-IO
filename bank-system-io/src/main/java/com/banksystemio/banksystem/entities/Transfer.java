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
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transferId;

    private BigDecimal transferAmount;

    private Long recipientId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate transferDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime transferTime;

    @ManyToOne
    @JsonIgnore
    private Account account;


    public Transfer() {
        transferDate = LocalDate.now();
        transferTime = LocalTime.now();

    }
}
