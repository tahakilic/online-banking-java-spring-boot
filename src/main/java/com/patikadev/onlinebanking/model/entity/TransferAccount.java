package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class TransferAccount extends Transfer {
    private String description;
    @OneToOne
    private Account toAccount;
    @OneToOne
    private Account fromAccount;

}
