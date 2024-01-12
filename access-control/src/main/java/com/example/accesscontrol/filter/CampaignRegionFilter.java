package com.example.accesscontrol.filter;

import com.example.accesscontrol.domain.Address;
import com.example.accesscontrol.domain.Campaign;

public class CampaignRegionFilter {
    public static boolean filterByRegion(Campaign campaign, Address address) {
        return "GLOBAL".equals(campaign.getRegionName()) ||
                        campaign.getRegionName().equals(address.getRegion());
    }
}
