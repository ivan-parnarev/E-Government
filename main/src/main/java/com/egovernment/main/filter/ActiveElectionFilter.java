package com.egovernment.main.filter;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.Election;
import com.egovernment.main.validation.ActiveCampaignValidator;

public class ActiveElectionFilter {

    public static boolean filterByRegionAndIsActive(Election election, String regionName) {
        Campaign campaign = election.getCampaign();
        return (null != campaign.getCampaignRegion()) && ("GLOBAL".equals(campaign.getCampaignRegion().toString()) ||
                election.getElectionRegion().equals(regionName))
                && ActiveCampaignValidator.isCampaignActive(campaign);
    }

}
