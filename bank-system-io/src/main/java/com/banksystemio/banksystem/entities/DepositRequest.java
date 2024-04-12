package com.banksystemio.banksystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositRequest {

    @Id
    private Long id;
    private BigDecimal amount;
    private LocalDate depositDate = LocalDate.now();
    private LocalTime depositTime = LocalTime.now();

    @ManyToOne
    private Account account;

}
