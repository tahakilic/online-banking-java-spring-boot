package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.AccountConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.response.AccountResponse;
import com.patikadev.onlinebanking.repository.AccountRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final CustomerRepository customerRepository;

    @Override
    public AccountResponse getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceOperationException.AccountNotValidException("Account not found!"));
        AccountResponse accountResponse = accountConverter.accountToAccountResponse(account);
        return accountResponse;
    }

    @Override
    public String createAccount(Long customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));

        Account account = accountConverter.accountRequestToAccount(customer,accountDTO);
        Account save = accountRepository.save(account);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public List<AccountResponse> getCustomerAllAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Customer not found!"));
        List<Account> customerAccounts = accountRepository.findByCustomer(customer);
        List<AccountResponse> accountResponse = accountConverter.accountListToAccountResponseList(customerAccounts);
        return accountResponse;

    }

    @Override
    public String deleteAccount(Long accountId) {
        return null;
    }
}
