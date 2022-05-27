package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.TransferConverter;
import com.patikadev.onlinebanking.exception.ServiceOperationException;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import com.patikadev.onlinebanking.repository.AccountRepository;
import com.patikadev.onlinebanking.repository.TransferAccountRepository;
import com.patikadev.onlinebanking.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final TransferConverter transferConverter;
    private final RestTemplate restTemplate;
    private final TransferAccountRepository transferAccountRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String accountToAccount(String fromIban, String toIban, TransferRequest transferRequest) {
        Account fromAccount = accountRepository.findByIban(fromIban);
        Account toAccount = accountRepository.findByIban(toIban);
        if(Objects.isNull(fromAccount)){
            throw new ServiceOperationException.TransferNotValidException("fromIban could not match the account!");
        }
        if(Objects.isNull(toAccount)){
            throw new ServiceOperationException.TransferNotValidException("toIban could not match the account!");
        }

        if (fromAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new ServiceOperationException.TransferNotValidException("Sender account is not active!");
        }
        if (toAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new ServiceOperationException.TransferNotValidException("Receiver account is not active!");
        }

        int res = fromAccount.getBalance().subtract(fromAccount.getLockedBalance()).compareTo(transferRequest.amount());
        if (res < 0) {
            throw new ServiceOperationException.TransferNotValidException("Insufficient balance!");
        }
        if (fromAccount.getAccountType() == AccountType.CHECKING_ACCOUNT && fromAccount.getCustomer() != toAccount.getCustomer()) {
            throw new ServiceOperationException.TransferNotValidException("It is a checking account, no transfer can be made!");
        }


        final String url = "https://api.apilayer.com/exchangerates_data/convert?to=" + toAccount.getCurrencyCode().toString()
                + "&from=" + fromAccount.getCurrencyCode().toString()
                + "&amount=" + transferRequest.amount().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "dmAvyZxsoS2vXUan6PhYHwUNrHm8Zbxd");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CurrencyDTO> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CurrencyDTO.class);
        CurrencyDTO currencyResponse = response.getBody();


        Account fromNewAccount = transferConverter.fromAccountUpdateBalance(fromAccount, transferRequest.amount());
        Account toNewAccount = transferConverter.toAccountUpdateBalance(toAccount, currencyResponse.result());

        accountRepository.save(toNewAccount);
        fromAccount.setLockedBalance(new BigDecimal(0));
        accountRepository.save(fromNewAccount);

        TransferAccount transferAccount = transferConverter.toTransferAccount(toNewAccount, fromNewAccount, transferRequest, currencyResponse);
        TransferAccount save = transferAccountRepository.save(transferAccount);
        //validator işlemi yapılacak

        return save.getId() != null ?"successful":"unsuccessful";
    }
}
