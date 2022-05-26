package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.AccountConverter;
import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AccountConverterImpl implements AccountConverter {
    @Override
    public AccountResponse accountToAccountResponse(Account account) {
        return new AccountResponse(account.getId(),
                                    account.getIban(),
                                    account.getBankCode(),
                                    account.getBranchCode(),
                                    account.getAccountNumber(),
                                    account.getCurrencyType(),
                                    account.getAccountStatus(),
                                    account.getAccountType(),
                                    account.getCreatedAt(),
                                    account.getBlockedAt(),
                                    account.getCanBeActiveAt(),
                                    account.getBalance(),
                                    account.getLockedBalance());
    }

    @Override
    public Account accountRequestToAccount(Customer customer, AccountDTO accountDTO) {
        Account account=accountDTOToAccount(accountDTO);
        account.setCustomer(customer);
        return account;
    }

    @Override
    public List<AccountResponse> accountListToAccountResponseList(List<Account> accounts) {
        List<AccountResponse> accountResponses=new ArrayList<>();
        for(Account account:accounts){
            accountResponses.add(new AccountResponse(account.getId(),
                    account.getIban(),
                    account.getBankCode(),
                    account.getBranchCode(),
                    account.getAccountNumber(),
                    account.getCurrencyType(),
                    account.getAccountStatus(),
                    account.getAccountType(),
                    account.getCreatedAt(),
                    account.getBlockedAt(),
                    account.getCanBeActiveAt(),
                    account.getBalance(),
                    account.getLockedBalance()));
        }
        return accountResponses;
    }

    public static Account accountDTOToAccount(AccountDTO accountDTO) {
        Account account=new Account();
        account.setAccountType(accountDTO.accountType());
        account.setAccountNumber(accountDTO.accountNumber());
        account.setCurrencyType(accountDTO.currencyType());
        account.setBankCode(accountDTO.bankCode());
        account.setBranchCode(accountDTO.branchCode());
        account.setAccountNumber(accountDTO.accountNumber());
        account.setIban(accountDTO.iban());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(new Date());
        return account;
    }
}