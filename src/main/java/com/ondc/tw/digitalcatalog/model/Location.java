package com.ondc.tw.digitalcatalog.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Location {

    @Id
    private UUID id;

    private Address address;

    private GPSCoordinates gpsCoordinates;
}
