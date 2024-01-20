package com.example.accesscontrol.filter;

import com.example.accesscontrol.domain.entity.Campaign;
import com.example.accesscontrol.validation.ActiveCampaignValidator;

public class CampaignRegionFilter {
    public static boolean filterByRegionAndIsActive(Campaign campaign, String regionName) {
        return (null != campaign.getRegionName()) && ("GLOBAL".equals(campaign.getRegionName()) ||
                        campaign.getRegionName().equals(regionName))
                && ActiveCampaignValidator.isCampaignActive(campaign);
    }
}
