package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import com.egovernment.accesscontrol.repository.FilteredCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilteredCampaignService {

    private final FilteredCampaignRepository filteredCampaignRepository;

    public void saveCampaign(FilteredCampaign campaign){
        this.filteredCampaignRepository.save(campaign);
    }

}
