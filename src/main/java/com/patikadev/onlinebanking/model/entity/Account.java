package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String branchCode;

    @Column(nullable = false)
    private Long accountNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CurrencyType currencyType;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType=AccountType.CHECKING_ACCOUNT;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedAt;

    @FutureOrPresent
    @Temporal(TemporalType.TIMESTAMP)
    private Date canBeActiveAt;

    @Column(nullable = false)
    private BigDecimal balance=BigDecimal.ZERO;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal lockedBalance=BigDecimal.ZERO;

    @ManyToOne
    private Customer customer;


}
