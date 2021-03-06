package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.AccountConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.facade.TransferFacade;
import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import com.patikadev.onlinebanking.model.response.AccountResponse;
import com.patikadev.onlinebanking.repository.AccountRepository;
import com.patikadev.onlinebanking.repository.CustomerRepository;
import com.patikadev.onlinebanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final CustomerRepository customerRepository;
    private final TransferFacade transferFacade;

    @Override
    public AccountResponse getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceOperationException.AccountNotValidException("Account not found!"));
        AccountResponse accountResponse = accountConverter.accountToAccountResponse(account);
        return accountResponse;
    }

    @Transactional
    @Override
    public String createAccount(Long customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.AccountNotValidException("Account not found!"));
        if(!(Objects.isNull(accountRepository.findByIban(accountDTO.iban())))){
            throw new ServiceOperationException.AccountNotValidException("No more than one is created from the same iban!");
        }
        Account account = accountConverter.accountRequestToAccount(customer,accountDTO);
        Account save = accountRepository.save(account);
        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Override
    public List<AccountResponse> getCustomerAllAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceOperationException.AccountNotValidException("Account not found!"));
        List<Account> customerAccounts = accountRepository.findByCustomer(customer);
        List<AccountResponse> accountResponse = accountConverter.accountListToAccountResponseList(customerAccounts);
        return accountResponse;

    }

    @Transactional
    @Override
    public String deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotValidException("Account not found!"));

        if(account.getBalance().compareTo(new BigDecimal(0)) != 0){
            throw new ServiceOperationException.AccountBalanceNotEmpty("Account balance is not empty!");
        }
        String operation="unsuccessful";
        if(account.getBalance().compareTo(new BigDecimal(0))==0){
            accountRepository.deleteById(accountId);
            operation="successful";
        }
        return operation;
    }

    @Transactional
    @Override
    public String atmPayToAccount(String iban, BigDecimal amount) {
        Account toAccount = accountRepository.findByIban(iban);
        if(Objects.isNull(toAccount)){
            throw new ServiceOperationException.AccountNotValidException("Account not found!");
        }
        CurrencyDTO currencyResponse = transferFacade.currencyControl(CurrencyCode.TRY, toAccount.getCurrencyCode(), amount);
        Account toNewAccount = accountConverter.atmToAccount(toAccount, currencyResponse.result());
        Account save = accountRepository.save(toNewAccount);
        return save.getId() != null ? "successful" : "unsuccessful";
    }
}
