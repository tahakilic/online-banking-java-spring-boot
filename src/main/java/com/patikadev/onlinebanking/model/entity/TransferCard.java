package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.TransferType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TransferCard extends Transfer {
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransferType transferType;
    @OneToOne
    private Card Card;
    @OneToOne
    private Account Account;
}
