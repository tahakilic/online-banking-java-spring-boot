package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.CardStatus;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Card extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String cardSecurityNumber;

    @Column(nullable = false)
    private String cardExpirationDate;

    @Column(nullable = false)
    private String cardPassword;

    @Column(nullable = false)
    private BigDecimal balance=BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal lockedBalance=BigDecimal.ZERO;

    @Column(nullable = false)
    private CurrencyCode currencyCode=CurrencyCode.TRY;

    private int cardLimit;
    private int currentLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedAt;

    @Enumerated(EnumType.ORDINAL)
    private CardStatus cardStatus;

    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;

    @ManyToOne
    private Customer customer;

}
