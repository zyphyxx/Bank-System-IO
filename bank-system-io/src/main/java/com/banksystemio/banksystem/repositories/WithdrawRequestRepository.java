package com.banksystemio.banksystem.repositories;

import com.banksystemio.banksystem.entities.WithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRequestRepository extends JpaRepository <WithdrawRequest,Long> {
}
