package com.patikadev.onlinebanking.repository;

import com.patikadev.onlinebanking.model.entity.TransferCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferCardRepository extends JpaRepository<TransferCard,Long> {
}
