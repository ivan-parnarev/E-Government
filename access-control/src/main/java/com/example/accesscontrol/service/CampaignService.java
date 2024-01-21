package com.example.accesscontrol.service;

import com.example.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.accesscontrol.domain.entity.Campaign;
import com.example.accesscontrol.filter.CampaignRegionFilter;
import com.example.accesscontrol.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Transactional
    public List<CampaignFilteredDTO> getActiveLocalCampaigns(String regionName) {

        return this.campaignRepository
                .findAll()
                .stream()
                .filter(c -> CampaignRegionFilter.filterByRegionAndIsActive(c, regionName))
                .map(this::mapCampaignToCampaignFilteredDTO)
                .collect(Collectors.toList());

    }

    private CampaignFilteredDTO mapCampaignToCampaignFilteredDTO(Campaign campaign) {
        return CampaignFilteredDTO.builder()
                .campaignTitle(campaign.getTitle())
                .regionName(campaign.getRegionName())
                .campaignId(campaign.getId())
                .campaignType(campaign.getCampaignType().name())
                .build();
    }

}
