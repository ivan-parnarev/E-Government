package com.example.accesscontrol.service;

import com.example.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.example.accesscontrol.filter.CampaignRegionFilter;
import com.example.accesscontrol.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<CampaignFilteredDTO> getActiveLocalCampaigns(String regionName) {

        return this.campaignRepository
                    .findAll()
                    .stream()
                    .filter(c -> CampaignRegionFilter.filterByRegionAndIsActive(c, regionName))
                    .map(c -> this.modelMapper.map(c, CampaignFilteredDTO.class))
                    .collect(Collectors.toList());

    }

}
