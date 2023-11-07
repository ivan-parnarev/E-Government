package com.egovernment.egovbackend.service;

import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignService campaignService;

    public CampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
}
