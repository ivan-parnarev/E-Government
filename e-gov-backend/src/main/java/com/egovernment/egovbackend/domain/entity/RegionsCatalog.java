package com.egovernment.egovbackend.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionsCatalog {

    private List<Region> regions = new ArrayList<>();

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
    public Optional<Region> findRegionById(int regionId) {
        return regions.stream()
                .filter(region -> region.getId() == regionId)
                .findFirst();
    }
}
