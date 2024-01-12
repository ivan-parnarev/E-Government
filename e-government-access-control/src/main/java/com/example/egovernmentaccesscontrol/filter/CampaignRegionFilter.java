package com.example.egovernmentaccesscontrol.filter;

import com.example.egovernmentaccesscontrol.domain.Address;
import com.example.egovernmentaccesscontrol.domain.Campaign;

public class CampaignRegionFilter {
    public static boolean filterByRegion(Campaign campaign, Address address) {
        return "GLOBAL".equals(campaign.getRegionName()) ||
                        campaign.getRegionName().equals(address.getRegion());
    }
}
