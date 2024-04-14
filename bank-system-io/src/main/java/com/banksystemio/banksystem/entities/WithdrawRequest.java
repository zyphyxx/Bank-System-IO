package com.banksystemio.banksystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class WithdrawRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate withdrawDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime withdrawTime;

    @ManyToOne
    @JsonIgnore
    private Account account;

    public WithdrawRequest(){
        withdrawDate = LocalDate.now();
        withdrawTime = LocalTime.now();
    }


}
