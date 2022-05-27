package com.patikadev.onlinebanking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseExtendEntity extends BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedAt;

}
