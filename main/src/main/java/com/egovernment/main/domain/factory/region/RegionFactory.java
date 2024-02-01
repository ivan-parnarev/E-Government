package com.egovernment.main.domain.factory.region;

import com.egovernment.main.domain.entity.Region;

public class RegionFactory implements RegionFactoryInterface {

    @Override

    public Region createRegion(int postalCode, String regionName){
        return Region.builder()
                .id(postalCode)
                .regionName(regionName)
                .build();
    }

}
