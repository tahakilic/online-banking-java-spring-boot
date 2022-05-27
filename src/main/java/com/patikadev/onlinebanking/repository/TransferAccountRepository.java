package com.patikadev.onlinebanking.repository;

import com.patikadev.onlinebanking.model.entity.TransferAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferAccountRepository extends JpaRepository<TransferAccount,Long> {
}
