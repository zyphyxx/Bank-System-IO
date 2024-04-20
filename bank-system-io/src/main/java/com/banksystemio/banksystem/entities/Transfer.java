package com.banksystemio.banksystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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

    @DecimalMin(value = "0.00", inclusive = false, message = "O valor de transferencia deve ser maior que zero")
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
