package com.egovernment.accesscontrol.filter;

import com.egovernment.accesscontrol.domain.entity.Address;
import com.egovernment.accesscontrol.domain.entity.Campaign;
import com.egovernment.accesscontrol.validation.ActiveCampaignValidator;

public class CampaignRegionFilter {
    public static boolean filterByRegionAndIsActive(Campaign campaign, String regionName) {
        return (null != campaign.getRegionName()) && ("GLOBAL".equals(campaign.getRegionName()) ||
                        campaign.getRegionName().equals(regionName))
                && ActiveCampaignValidator.isCampaignActive(campaign);
    }
}
