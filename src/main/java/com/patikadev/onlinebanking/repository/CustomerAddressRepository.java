package com.patikadev.onlinebanking.repository;

import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {
    CustomerAddress findByCustomer(Long id);
    List<CustomerAddress> findByCustomer(Customer customer);
}
