package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.CardStatus;
import com.patikadev.onlinebanking.model.enums.CardType;
import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Card extends BaseEntity {

    @Column(nullable = false,unique = true)
    private Long cardNumber;

    @Column(nullable = false,length = 3)
    private String cardSecurityNumber;

    @Column(nullable = false)
    private String cardExpirationDate;

    @Column(nullable = false)
    private String cardPassword;

    @Column(nullable = false)
    private BigDecimal balance=BigDecimal.ZERO;

    @Column(nullable = false)
    private CurrencyCode currencyCode=CurrencyCode.TRY;

    private BigDecimal cardLimit; //kredi kartı ise zorunlu

    private BigDecimal currentLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CardStatus cardStatus=CardStatus.ACTIVE;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CardType cardType;

    @ManyToOne
    private Customer customer;

}
