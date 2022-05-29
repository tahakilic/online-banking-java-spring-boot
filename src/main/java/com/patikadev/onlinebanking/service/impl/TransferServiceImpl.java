package com.patikadev.onlinebanking.service.impl;

import com.patikadev.onlinebanking.converter.TransferConverter;
import com.patikadev.onlinebanking.facade.TransferFacade;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.entity.TransferCard;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import com.patikadev.onlinebanking.repository.AccountRepository;
import com.patikadev.onlinebanking.repository.CardRepository;
import com.patikadev.onlinebanking.repository.TransferAccountRepository;
import com.patikadev.onlinebanking.repository.TransferCardRepository;
import com.patikadev.onlinebanking.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final TransferConverter transferConverter;
    private final TransferAccountRepository transferAccountRepository;
    private final CardRepository cardRepository;
    private final TransferFacade transferFacade;
    private final TransferCardRepository transferCardRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String accountToAccount(String fromIban, String toIban, TransferRequest transferRequest,BigDecimal amount) {
        Account fromAccount = accountRepository.findByIban(fromIban);
        Account toAccount = accountRepository.findByIban(toIban);

        transferFacade.accountToAccountException(fromAccount,toAccount,transferRequest,amount);

        CurrencyDTO currencyResponse = transferFacade.currencyControl(fromAccount.getCurrencyCode(),toAccount.getCurrencyCode(),amount);

        Account fromNewAccount = transferConverter.fromAccountUpdateBalance(fromAccount,amount);
        Account toNewAccount = transferConverter.toAccountUpdateBalance(toAccount, currencyResponse.result());

        accountRepository.save(toNewAccount);
        accountRepository.save(fromNewAccount);

        TransferAccount transferAccount = transferConverter.toTransferAccount(toNewAccount, fromNewAccount, transferRequest, currencyResponse);
        TransferAccount save = transferAccountRepository.save(transferAccount);

        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String accountToCard(BigDecimal amount, String fromAccountIban, Long toCardNumber) {
        Account fromAccount = accountRepository.findByIban(fromAccountIban);
        Card toCard=cardRepository.findByCardNumber(toCardNumber);

        transferFacade.accountToCardException(fromAccount,toCard,amount);

        CurrencyDTO currencyResponse = transferFacade.currencyControl(fromAccount.getCurrencyCode(), toCard.getCurrencyCode(), amount);

        Account fromNewAccount = transferConverter.fromAccountUpdateBalance(fromAccount, amount);
        Card toNewCard=transferConverter.toCardUpdateBalance(toCard,currencyResponse.result());

        cardRepository.save(toNewCard);
        accountRepository.save(fromNewAccount);

        TransferCard transferCard = transferConverter.toTransferCard(toNewCard, fromNewAccount, currencyResponse);
        TransferCard save = transferCardRepository.save(transferCard);

        return save.getId() != null ?"successful":"unsuccessful";
    }

    @Transactional
    @Override
    public String cardToAccount(Long fromCardNumber, String toAccountIban, BigDecimal amount) {
        Card fromCard=cardRepository.findByCardNumber(fromCardNumber);
        Account toAccount = accountRepository.findByIban(toAccountIban);

        transferFacade.cardToAccountException(fromCard,toAccount,amount);
        CurrencyDTO currencyResponse = transferFacade.currencyControl(fromCard.getCurrencyCode(), toAccount.getCurrencyCode(), amount);

        Card fromNewCard = transferConverter.fromCardUpdateBalance(fromCard, amount);
        Account toNewAccount = transferConverter.toAccountUpdateBalance(toAccount, currencyResponse.result());

        accountRepository.save(toNewAccount);
        cardRepository.save(fromNewCard);

        TransferCard transferCardToAccount = transferConverter.cardToAccount(fromNewCard, toNewAccount, currencyResponse);
        TransferCard save = transferCardRepository.save(transferCardToAccount);

        return save.getId() != null ?"successful":"unsuccessful";
    }

}
