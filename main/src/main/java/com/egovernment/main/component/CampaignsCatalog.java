package com.egovernment.main.component;

import com.egovernment.main.domain.entity.Campaign;
import com.egovernment.main.repository.CampaignRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CampaignsCatalog {

    private final CampaignRepository campaignRepository;

    public List<Campaign> campaigns = new ArrayList<>();

    public void loadCampaigns() {
        List<Campaign> allCampaigns = this.campaignRepository.findAll();
        this.campaigns.addAll(allCampaigns);
    }
}
