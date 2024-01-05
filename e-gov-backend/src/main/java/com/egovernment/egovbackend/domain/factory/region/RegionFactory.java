package com.egovernment.egovbackend.domain.factory.region;

import com.egovernment.egovbackend.domain.entity.Region;

public class RegionFactory implements RegionFactoryInterface {

    @Override
    public Region createRegion(byte id, String englishRegionName
            , String bulgarianRegionName){
        return Region.builder()
                .id(id)
                .englishRegionName(englishRegionName)
                .bulgarianRegionName(bulgarianRegionName)
                .build();
    }

}
