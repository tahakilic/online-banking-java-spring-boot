package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.service.CustomerService;
import com.patikadev.onlinebanking.repository.validator.CreateCustomerRequestValidator;
import com.patikadev.onlinebanking.repository.validator.EmailValidator;
import com.patikadev.onlinebanking.repository.validator.IDValidator;
import com.patikadev.onlinebanking.repository.validator.UpdateCustomerRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final IDValidator idValidator;
    private final CreateCustomerRequestValidator createCustomerRequestValidator;
    private final UpdateCustomerRequestValidator updateCustomerRequestValidator;
    private final EmailValidator emailValidator;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        createCustomerRequestValidator.validate(customerRequest);
        emailValidator.validate(customerRequest.contactInformation().primaryEmail());
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody UpdateCustomerRequest customerRequest) {
        idValidator.validate(customerRequest.id());
        updateCustomerRequestValidator.validate(customerRequest);
        emailValidator.validate(customerRequest.contactInformation().primaryEmail());
        return ResponseEntity.ok(customerService.updateCustomer(customerRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }


}
