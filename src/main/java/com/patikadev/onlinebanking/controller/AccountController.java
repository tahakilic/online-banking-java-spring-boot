package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.response.AccountResponse;
import com.patikadev.onlinebanking.service.AccountService;
import com.patikadev.onlinebanking.validator.AmountValidator;
import com.patikadev.onlinebanking.validator.CreateAccountDTOValidator;
import com.patikadev.onlinebanking.validator.IDValidator;
import com.patikadev.onlinebanking.validator.IbanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/accounts")
public class AccountController {
    private final IDValidator idValidator;
    private final AccountService accountService;
    private final CreateAccountDTOValidator createAccountRequestValidator;
    private final AmountValidator amountValidator;
    private final IbanValidator ibanValidator;

    @GetMapping(path = "/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        idValidator.validate(accountId);
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

    @PostMapping(path = "/customers/{customerId}")
    public ResponseEntity<String> createAccount(@PathVariable Long customerId, @RequestBody AccountDTO accountDTO) {
        idValidator.validate(customerId);
        createAccountRequestValidator.validate(accountDTO);
        return ResponseEntity.ok(accountService.createAccount(customerId, accountDTO));
    }

    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<List<AccountResponse>> getCustomerAllAccount(@PathVariable Long customerId) {
        idValidator.validate(customerId);
        return ResponseEntity.ok(accountService.getCustomerAllAccount(customerId));
    }


    @DeleteMapping(path = "{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        idValidator.validate(accountId);
        return ResponseEntity.ok(accountService.deleteAccount(accountId));
    }

    @PutMapping
    public ResponseEntity<String> atmPayToAccount(@RequestParam String iban,
                                                  @RequestParam BigDecimal amount
                                                  ) {
        amountValidator.validate(amount);
        ibanValidator.validate(iban);
        return ResponseEntity.ok(accountService.atmPayToAccount(iban, amount));
    }

}
