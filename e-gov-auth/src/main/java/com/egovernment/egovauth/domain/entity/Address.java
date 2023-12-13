package com.egovernment.egovauth.domain.entity;

import com.egovernment.egovauth.domain.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Country country;
    @ManyToOne
    private Region region;
    @ManyToOne
    private Municipality municipality;
    @ManyToOne
    private City city;
    @ManyToOne
    private Village village;

}
