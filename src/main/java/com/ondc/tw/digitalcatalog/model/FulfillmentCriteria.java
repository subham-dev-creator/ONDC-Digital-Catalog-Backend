package com.ondc.tw.digitalcatalog.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class FulfillmentCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    FulfillmentType fulfillmentType;
}
