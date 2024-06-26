package com.banksystemio.banksystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@Builder
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.00", inclusive = false, message = "O valor de deposito deve ser maior que zero")
    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate depositDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime depositTime;

    public Deposit(){
        depositDate = LocalDate.now();
        depositTime = LocalTime.now();
    }

    @ManyToOne
    @JsonIgnore
    private Account account;

}
