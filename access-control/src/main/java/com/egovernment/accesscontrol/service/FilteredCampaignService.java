package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import com.egovernment.accesscontrol.repository.FilteredCampaignRepository;
import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.filter.CampaignRegionFilter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilteredCampaignService {
    private final FilteredCampaignRepository filteredCampaignRepository;

    @Transactional
    public List<CampaignFilteredDTO> getActiveLocalCampaigns(String regionName) {

        return this.filteredCampaignRepository
                .findAll()
                .stream()
                .filter(c -> CampaignRegionFilter.filterByRegionAndIsActive(c, regionName))
                .map(this::mapCampaignToCampaignFilteredDTO)
                .collect(Collectors.toList());

    }

    private CampaignFilteredDTO mapCampaignToCampaignFilteredDTO(FilteredCampaign campaign) {

        return CampaignFilteredDTO.builder()
                .campaignTitle(campaign.getCampaignTitle())
                .regionName(campaign.getRegionName())
                .campaignId(campaign.getId())
                .campaignType(campaign.getCampaignType())
                .build();

    }
    public void saveCampaign(FilteredCampaign campaign){
        this.filteredCampaignRepository.save(campaign);
    }

}
