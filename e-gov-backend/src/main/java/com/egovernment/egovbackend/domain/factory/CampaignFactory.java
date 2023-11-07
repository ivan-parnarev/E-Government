package com.egovernment.egovbackend.domain.factory;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;

import java.time.LocalDateTime;

public class CampaignFactory implements CampaignFactoryInterface{

    @Override
    public Campaign createCampaign(CampaignType type, String campaignTopic, User from, int duration, String answersJson) {
        //check user is administrator
        return Campaign.builder()
                .campaignType(type)
                .campaignTopic(campaignTopic)
                .from(from)
                .date(LocalDateTime.now())
                .duration(duration)
                .answersJson(answersJson)
                .build();
    }
}
