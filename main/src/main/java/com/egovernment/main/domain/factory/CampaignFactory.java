package com.egovernment.main.domain.factory;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.CampaignType;

import java.time.LocalDateTime;

public class CampaignFactory implements CampaignFactoryInterface{

    @Override
    public Campaign createCampaign(CampaignType type, String title, String description
            , User from, LocalDateTime startDate, LocalDateTime endDate, boolean isActive,
                                   String campaignRegion, Long campaignReferenceId) {

        return Campaign.builder()
                .campaignType(type)
                .title(title)
                .description(description)
                .from(from)
                .startDate(startDate)
                .endDate(endDate)
                .isActive(isActive)
                .build();
    }
}
