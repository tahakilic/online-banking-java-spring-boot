package com.patikadev.onlinebanking.entity;

import com.patikadev.onlinebanking.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends BaseExtendModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany
    private List<Card> cards;






}
