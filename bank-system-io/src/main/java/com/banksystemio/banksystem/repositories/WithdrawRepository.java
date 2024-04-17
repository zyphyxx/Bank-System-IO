package com.banksystemio.banksystem.repositories;

import com.banksystemio.banksystem.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository <Withdraw,Long> {
}
