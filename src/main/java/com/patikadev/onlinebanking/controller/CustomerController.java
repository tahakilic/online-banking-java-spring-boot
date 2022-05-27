package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.dto.CustomerAddressDTO;
import com.patikadev.onlinebanking.model.request.CreateCustomerRequest;
import com.patikadev.onlinebanking.model.request.UpdateCustomerRequest;
import com.patikadev.onlinebanking.model.response.CustomerAddressResponse;
import com.patikadev.onlinebanking.model.response.CustomerResponse;
import com.patikadev.onlinebanking.service.CustomerService;
import com.patikadev.onlinebanking.validator.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final IDValidator idValidator;
    private final CreateCustomerRequestValidator createCustomerRequestValidator;
    private final UpdateCustomerRequestValidator updateCustomerRequestValidator;
    private final EmailValidator emailValidator;
    private final CustomerAddressValidator customerAddressValidator;
    private final CreateAccountDTOValidator createAccountValidator;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long customerId) {
        idValidator.validate(customerId);
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        createCustomerRequestValidator.validate(customerRequest);
        createAccountValidator.validate(customerRequest.account());
        emailValidator.validate(customerRequest.contactInformation().primaryEmail());
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        idValidator.validate(updateCustomerRequest.id());
        updateCustomerRequestValidator.validate(updateCustomerRequest);
        emailValidator.validate(updateCustomerRequest.contactInformation().primaryEmail());
        return ResponseEntity.ok(customerService.updateCustomer(updateCustomerRequest));

    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId){
        //!!!!!!Güncellenilecek  Unutma!! Kredi kartı borcu veya hesabında para var ise silinmeyecek
        idValidator.validate(customerId);
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }

    @PutMapping(path = "/addresses/{customerAddressId}")
    public ResponseEntity<String> updateCustomerAddress(@PathVariable Long customerAddressId,
                                                        @RequestBody CustomerAddressDTO customerAddressDTO) {
        idValidator.validate(customerAddressId);
        customerAddressValidator.validate(customerAddressDTO);
        return ResponseEntity.ok(customerService.updateCustomerAddress(customerAddressId,customerAddressDTO));
    }

    @GetMapping(path = "/{customerId}/addresses")
    public ResponseEntity<List<CustomerAddressResponse>> getCustomerAllAddress(@PathVariable Long customerId) {
        idValidator.validate(customerId);
        return ResponseEntity.ok(customerService.getCustomerAllAddress(customerId));
    }

    @PostMapping(path = "/{customerId}/addresses")
    public ResponseEntity<String> addCustomerAddress(@PathVariable Long customerId,@RequestBody CustomerAddressDTO customerAddressDTO) {
        idValidator.validate(customerId);
        customerAddressValidator.validate(customerAddressDTO);
        return ResponseEntity.ok(customerService.addCustomerAddress(customerId,customerAddressDTO));
    }

    @DeleteMapping(path = "/addresses/{customerAddressId}")
    public ResponseEntity<String> deleteCustomerAddress(@PathVariable Long customerAddressId){
        idValidator.validate(customerAddressId);
        return ResponseEntity.ok(customerService.deleteCustomerAddress(customerAddressId));
    }




}
