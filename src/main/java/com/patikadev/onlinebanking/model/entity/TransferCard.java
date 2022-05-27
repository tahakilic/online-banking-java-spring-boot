package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class TransferCard extends Transfer {

    @OneToOne
    private Card fromCard;
    @OneToOne
    private Account toAccount;
}
