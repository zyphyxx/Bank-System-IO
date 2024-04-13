package com.banksystemio.banksystem.repositories;


import com.banksystemio.banksystem.entities.DepositRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRequestRepository extends JpaRepository<DepositRequest,Long> {
}
