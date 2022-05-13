package com.patikadev.onlinebanking.entity;

import com.patikadev.onlinebanking.model.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transfer extends BaseModel{

    private BigDecimal amount;
    private CurrencyCode currencyCode;
    private BigDecimal exchangeRate;
    private String description;
    private Date createdAt;

    @ManyToOne
    private Account toAccount;

    @ManyToOne
    private Account fromAccount;
}
