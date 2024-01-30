package com.egovernment.main.domain.dto.region;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String country;
    private String region;
    private String municipality;
    private String postcode;
    private String city;
    private String village;

}