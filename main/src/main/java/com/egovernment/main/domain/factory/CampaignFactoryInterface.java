package com.egovernment.main.domain.factory;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.domain.entity.User;
import com.egovernment.main.domain.enums.CampaignRegion;
import com.egovernment.main.domain.enums.CampaignType;

import java.time.LocalDateTime;


public interface CampaignFactoryInterface {
    Campaign createCampaign(CampaignType type, String title, String description,
                            User from, LocalDateTime startDate,
                            LocalDateTime endDate, boolean isActive,
                            CampaignRegion campaignRegion);
}
