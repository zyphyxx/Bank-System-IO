package com.banksystemio.banksystem.repositories;

import com.banksystemio.banksystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {

}
