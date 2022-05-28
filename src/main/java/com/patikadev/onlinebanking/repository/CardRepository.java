package com.patikadev.onlinebanking.repository;

import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByCardNumber(String cardNumber);

    List<Card> findByCustomer(Customer customer);
}
