package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.AccountStatus;
import com.patikadev.onlinebanking.model.enums.AccountType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
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

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String branchCode;

    @Column(nullable = false)
    private Long accountNumber;

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
}
