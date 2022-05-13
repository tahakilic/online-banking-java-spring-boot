package com.patikadev.onlinebanking.entity;

import com.patikadev.onlinebanking.model.enums.CardType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Card extends BaseExtendModel{
    private String cardNumber;
    private BigDecimal usableLimit;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Account account;


}
