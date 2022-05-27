package com.patikadev.onlinebanking.service;

import com.patikadev.onlinebanking.model.request.TransferRequest;

public interface TransferService {
    String accountToAccount(String fromAccount, String toAccount, TransferRequest transferRequest);
}
