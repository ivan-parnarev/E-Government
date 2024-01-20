package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.filter.CampaignRegionFilter;
import com.egovernment.accesscontrol.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
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
