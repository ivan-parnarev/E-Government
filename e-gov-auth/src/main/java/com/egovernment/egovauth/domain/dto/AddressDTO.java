package com.egovernment.egovauth.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDTO {

    private String country;
    private String region;
    private String municipality;
    private String city;
    private String village;
}
