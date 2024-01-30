package com.egovernment.accesscontrol.validation;

import com.egovernment.accesscontrol.domain.entity.Campaign;

import java.time.LocalDateTime;

public class ActiveCampaignValidator {

    public static boolean isCampaignActive(Campaign campaign) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (campaign == null) {
            return false;
        }

        LocalDateTime startDate = campaign.getStartDate();
        LocalDateTime endDate = campaign.getEndDate();

        return (startDate == null || !currentTime.isBefore(startDate)) &&
                (endDate == null || !currentTime.isAfter(endDate));
    }

}
