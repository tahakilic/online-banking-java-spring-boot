package com.patikadev.onlinebanking.converter;

import com.patikadev.onlinebanking.model.dto.AccountDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Customer;
import com.patikadev.onlinebanking.model.response.AccountResponse;

import java.util.List;

public interface AccountConverter  {
    AccountResponse accountToAccountResponse(Account account);
    List<AccountResponse> accountListToAccountResponseList(List<Account> account);

    Account accountRequestToAccount(Customer customer, AccountDTO accountRequest);
}
