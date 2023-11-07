package com.egovernment.egovbackend.domain.factory;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;


public interface CampaignFactoryInterface {
    Campaign createCampaign(CampaignType type, String campaignTopic, User from, int duration, String answersJson);
}
