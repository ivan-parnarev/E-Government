package com.egovernment.egovbackend.domain.factory.region;

import com.egovernment.egovbackend.domain.entity.Region;

public interface RegionFactoryInterface {
    Region createRegion(int id, String englishRegionName, String bulgarianRegionName);
}
