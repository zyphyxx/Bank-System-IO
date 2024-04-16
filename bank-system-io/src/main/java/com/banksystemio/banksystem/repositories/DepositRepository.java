package com.banksystemio.banksystem.repositories;


import com.banksystemio.banksystem.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
}
