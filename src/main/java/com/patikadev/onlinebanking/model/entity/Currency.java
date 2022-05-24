package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Currency extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false,length = 3)
    private String code;

    private String symbol;
}