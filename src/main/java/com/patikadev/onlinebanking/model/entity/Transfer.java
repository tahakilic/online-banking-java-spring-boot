package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Transfer extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amount;
    private CurrencyCode amountCurrencyCode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyCode fromCurrencyCode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyCode toCurrencyCode;
    @Column(nullable = false)
    private BigDecimal exchangeRate;
    private Date createdAt;



}
