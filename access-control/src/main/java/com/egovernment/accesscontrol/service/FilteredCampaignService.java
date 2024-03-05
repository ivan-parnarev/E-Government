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
        CampaignFilteredDTO result = CampaignFilteredDTO
                .builder()
                .campaignTitle(campaign.getCampaignTitle())
                .campaignType(campaign.getCampaignType())
                .startDate(campaign.getStartDate())
                .endDate(campaign.getEndDate())
                .build();

        if(campaign.getCampaignType().equals("VOTING")){
            result.setRegionName(campaign.getRegionName());
            result.setElectionId(campaign.getElectionId());
            result.setElectionType(campaign.getElectionType());

        }else if(campaign.getCampaignType().equals("CENSUS")){
            result.setCampaignId(campaign.getCampaignId());
            result.setRegionName("GLOBAL");
        }
        return result;
    }
    public void saveCampaign(FilteredCampaign campaign){
        this.filteredCampaignRepository.save(campaign);
    }

}
