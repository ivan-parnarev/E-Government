package com.egovernment.egovbackend.domain.factory;

import com.egovernment.egovbackend.domain.entity.Campaign;
import com.egovernment.egovbackend.domain.entity.User;
import com.egovernment.egovbackend.domain.enums.CampaignType;

import java.time.LocalDateTime;


public interface CampaignFactoryInterface {
    Campaign createCampaign(CampaignType type, String title, String description,
                            User from, LocalDateTime startDate,
                            LocalDateTime endDate, boolean isActive,
                            String campaignRegion, Long campaignReferenceId);
}
