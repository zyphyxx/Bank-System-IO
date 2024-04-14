package com.banksystemio.banksystem.repositories;

import com.banksystemio.banksystem.entities.TransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRequestRepository extends JpaRepository<TransferRequest, Long> {
}
