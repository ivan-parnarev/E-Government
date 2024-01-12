package com.example.accesscontrol.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String region;
    private String municipality;
    private String postcode;
    private String city;
    private String village;
}