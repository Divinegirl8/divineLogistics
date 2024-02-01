package org.logistics.data.model;

import lombok.Data;

@Data
public class Address {
    private String country;
    private String state;
    private String houseNumber;
    private String streetName;
    private String postalCode;
}
