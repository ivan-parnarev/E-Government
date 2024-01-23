package com.egovernment.main.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionsCatalog {

    private List<Region> regions = new ArrayList<>();

    public Optional<Region> findRegionById(int regionId) {
        return regions.stream()
                .filter(region -> region.getId() == regionId)
                .findFirst();
    }
}
