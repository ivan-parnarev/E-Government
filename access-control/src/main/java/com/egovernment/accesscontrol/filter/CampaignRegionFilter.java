package com.egovernment.accesscontrol.filter;

import com.egovernment.accesscontrol.domain.entity.Address;
import com.egovernment.accesscontrol.domain.entity.Campaign;

public class CampaignRegionFilter {
    public static boolean filterByRegion(Campaign campaign, Address address) {
        return "GLOBAL".equals(campaign.getRegionName()) ||
                        campaign.getRegionName().equals(address.getRegion());
    }
}
