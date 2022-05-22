package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account extends BaseExtendModel{

    private String IBAN;
    private BigDecimal balance;
    private CurrencyCode currencyCode;
    private AccountType accountType;  //DEPOSIT (demand deposit account), SAVINGS(savings account)

    @ManyToOne
    private Customer customer;
}
