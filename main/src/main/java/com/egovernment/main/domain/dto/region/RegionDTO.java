package com.egovernment.main.domain.dto.region;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegionDTO {

    private int id;
    private String englishRegionName;
    private String bulgarianRegionName;

}
