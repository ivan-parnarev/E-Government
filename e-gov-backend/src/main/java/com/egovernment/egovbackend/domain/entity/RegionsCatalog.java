package com.egovernment.egovbackend.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionsCatalog {

    private static RegionsCatalog instance;
    private List<Region> regions = new ArrayList<>();

    public static RegionsCatalog getInstance() {
        if (instance == null) {
            instance = new RegionsCatalog();
        }
        return instance;
    }

}
