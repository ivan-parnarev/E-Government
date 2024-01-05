package com.egovernment.egovbackend.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public Optional<Region> findRegionByEnglishName(String englishName) {
        return regions.stream()
                .filter(region -> region.getEnglishRegionName().equalsIgnoreCase(englishName))
                .findFirst();
    }

    public Optional<Region> findRegionByBulgarianName(String bulgarianName) {
        return regions.stream()
                .filter(region -> region.getBulgarianRegionName().equalsIgnoreCase(bulgarianName))
                .findFirst();
    }

}
