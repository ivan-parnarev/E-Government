package com.egovernment.main.domain.dto.address;

import lombok.Data;

@Data
public class AddressDTO {
    private String country;
    private String region;
    private String postcode;
    private String municipality;
    private String city;
    private String village;
}
