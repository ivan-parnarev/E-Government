package com.egovernment.main.domain.factory.region;

import com.egovernment.main.domain.entity.Region;

public interface RegionFactoryInterface {

    Region createRegion(int postalCode, String regionName);

}
