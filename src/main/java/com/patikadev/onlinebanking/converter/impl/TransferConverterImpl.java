package com.patikadev.onlinebanking.converter.impl;

import com.patikadev.onlinebanking.converter.TransferConverter;
import com.patikadev.onlinebanking.model.dto.CurrencyDTO;
import com.patikadev.onlinebanking.model.entity.Account;
import com.patikadev.onlinebanking.model.entity.Card;
import com.patikadev.onlinebanking.model.entity.TransferAccount;
import com.patikadev.onlinebanking.model.entity.TransferCard;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.enums.TransferType;
import com.patikadev.onlinebanking.model.request.TransferRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class TransferConverterImpl implements TransferConverter {
    @Override
    public Account toAccountUpdateBalance(Account toAccount, BigDecimal amount) {
        toAccount.setBalance(toAccount.getBalance().add(amount));
        return toAccount;
    }

    @Override
    public Account fromAccountUpdateBalance(Account fromAccount, BigDecimal amount) {
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        return fromAccount;
    }

    @Override
    public Card toCardUpdateBalance(Card toCard, BigDecimal amount) {
        if(toCard.getCardType()== CardType.CREDIT_CARD){
            toCard.setBalance(toCard.getBalance().subtract(amount));
            return toCard;
        }
        toCard.setBalance(toCard.getBalance().add(amount));
        return toCard;

    }

    @Override
    public Card fromCardUpdateBalance(Card fromCard, BigDecimal amount) {
        fromCard.setBalance(fromCard.getBalance().subtract(amount));
        return fromCard;
    }

    @Override
    public TransferAccount toTransferAccount(Account toNewAccount, Account fromNewAccount, TransferRequest transferRequest, CurrencyDTO currencyDTO) {
        TransferAccount transferAccount=new TransferAccount();
        transferAccount.setToAccount(toNewAccount);
        transferAccount.setFromAccount(fromNewAccount);
        transferAccount.setAmount(currencyDTO.result());
        transferAccount.setAmountCurrencyCode(toNewAccount.getCurrencyCode());
        transferAccount.setFromCurrencyCode(fromNewAccount.getCurrencyCode());
        transferAccount.setToCurrencyCode(toNewAccount.getCurrencyCode());
        transferAccount.setExchangeRate(currencyDTO.info().rate());
        transferAccount.setDescription(transferRequest.description());
        transferAccount.setCreatedAt(new Date());

        return transferAccount;
    }

    @Override
    public TransferCard toTransferCard(Card toNewCard, Account fromNewAccount, CurrencyDTO currencyDTO) {
        TransferCard transferCard=new TransferCard();
        transferCard.setAccount(fromNewAccount);
        transferCard.setCard(toNewCard);
        transferCard.setAmount(currencyDTO.result());
        transferCard.setAmountCurrencyCode(toNewCard.getCurrencyCode());
        transferCard.setFromCurrencyCode(fromNewAccount.getCurrencyCode());
        transferCard.setToCurrencyCode(toNewCard.getCurrencyCode());
        transferCard.setExchangeRate(currencyDTO.info().rate());
        transferCard.setTransferType(TransferType.FROM_ACCOUNT_TO_CARD);
        transferCard.setCreatedAt(new Date());

        return transferCard;
    }

    @Override
    public TransferCard cardToAccount(Card fromNewCard, Account toNewAccount, CurrencyDTO currencyResponse) {
        TransferCard transferCard=new TransferCard();
        transferCard.setAccount(toNewAccount);
        transferCard.setCard(fromNewCard);
        transferCard.setAmount(currencyResponse.result());
        transferCard.setAmountCurrencyCode(toNewAccount.getCurrencyCode());
        transferCard.setFromCurrencyCode(fromNewCard.getCurrencyCode());
        transferCard.setToCurrencyCode(toNewAccount.getCurrencyCode());
        transferCard.setExchangeRate(currencyResponse.info().rate());
        transferCard.setTransferType(TransferType.FROM_CARD_TO_ACCOUNT);
        transferCard.setCreatedAt(new Date());
        return transferCard;
    }
}
