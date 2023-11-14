package com.egovernment.egovbackend.domain.factory;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;

import java.time.LocalDateTime;

public class CampaignFactory implements CampaignFactoryInterface{

    @Override
    public Campaign createCampaign(CampaignType type, String title, String description
            , User from, LocalDateTime startDate, LocalDateTime endDate, boolean isActive) {
        //check user is administrator
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
