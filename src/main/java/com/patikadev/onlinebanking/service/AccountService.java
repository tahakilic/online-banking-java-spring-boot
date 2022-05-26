package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse getAccount(Long accountId);
    String  createAccount(Long customerId, AccountDTO accountRequest);

    List<AccountResponse> getCustomerAllAccount(Long customerId);

    String deleteAccount(Long accountId);
}
