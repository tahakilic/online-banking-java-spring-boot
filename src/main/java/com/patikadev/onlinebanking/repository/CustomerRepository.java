package com.patikadev.onlinebanking.repository;

import com.patikadev.onlinebanking.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByIdentityNumber(Long identityNumber);
}
