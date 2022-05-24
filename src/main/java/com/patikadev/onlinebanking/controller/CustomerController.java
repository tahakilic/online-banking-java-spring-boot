package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.CustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.service.CustomerService;
import com.patikadev.onlinebanking.validator.CreateCustomerRequestValidator;
import com.patikadev.onlinebanking.validator.IDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final IDValidator idValidator;
    private final CreateCustomerRequestValidator createCustomerRequestValidator;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id ){
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        createCustomerRequestValidator.validate(customerRequest);
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }


}
