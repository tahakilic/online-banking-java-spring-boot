package com.patikadev.onlinebanking.model.entity;

import com.patikadev.onlinebanking.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer extends BaseExtendEntity {

    @Column(nullable = false,unique = true)
    private Long identityNumber;
    @Column(nullable = false)
    private String name;
    private String middleName;
    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Past(message = "birthDay must be a past date!")
    private Date birthDay;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Gender gender;

    @Embedded
    private ContactInformation contactInformation;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CustomerAddress> customerAddress = new HashSet<>();



    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Account> accounts=new HashSet<>();


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Card> cards=new HashSet<>();

    @Transient
    public String getFullName () {
        return getName() + " " + (StringUtils.hasLength(getMiddleName()) ? getMiddleName() : "") + getLastName();
    }
    public void addAddress(CustomerAddress customerAddress){
        customerAddress.setCustomer(this);
        this.customerAddress.add(customerAddress);
    }
    public void addAccount(Account account){
        account.setCustomer(this);
        this.accounts.add(account);
    }







}
