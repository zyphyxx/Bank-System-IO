package com.banksystemio.banksystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.00", inclusive = false, message = "O valor de saque deve ser maior que zero")
    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate withdrawDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime withdrawTime;

    @ManyToOne
    @JsonIgnore
    private Account account;

    public Withdraw(){
        withdrawDate = LocalDate.now();
        withdrawTime = LocalTime.now();
    }


}
