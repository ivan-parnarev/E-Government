package com.egovernment.main.domain.factory.region;

import com.egovernment.main.domain.entity.Region;

public class RegionFactory implements RegionFactoryInterface {

    @Override
    public Region createRegion(int id, String englishRegionName
            , String bulgarianRegionName){
        return Region.builder()
                .id(id)
                .englishRegionName(englishRegionName)
                .bulgarianRegionName(bulgarianRegionName)
                .build();
    }

}
