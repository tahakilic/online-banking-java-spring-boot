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
    @Column(nullable = false)
    private CurrencyCode fromCurrencyCode;
    @Column(nullable = false)
    private CurrencyCode toCurrencyCode;
    @Column(nullable = false)
    private BigDecimal exchangeRate;
    private String description;
    private Date createdAt;



}
