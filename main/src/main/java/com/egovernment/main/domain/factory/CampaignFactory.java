package com.egovernment.main.domain.factory;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.CampaignRegion;
import com.egovernment.main.domain.enums.CampaignStatus;
import com.egovernment.main.domain.enums.CampaignType;

import java.time.LocalDateTime;

public class CampaignFactory implements CampaignFactoryInterface{

    @Override
    public Campaign createCampaign(CampaignType type, String title, String description
            , User from, LocalDateTime startDate, LocalDateTime endDate, boolean isActive,
                                   CampaignRegion campaignRegion, CampaignStatus campaignStatus) {

        return Campaign.builder()
                .campaignType(type)
                .title(title)
                .description(description)
                .from(from)
                .startDate(startDate)
                .endDate(endDate)
                .campaignRegion(campaignRegion)
                .isActive(isActive)
                .campaignStatus(campaignStatus)
                .build();
    }
}
