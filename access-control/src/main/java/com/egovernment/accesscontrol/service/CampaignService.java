package com.egovernment.accesscontrol.service;

import com.egovernment.accesscontrol.repository.CampaignRepository;
import com.egovernment.accesscontrol.validation.ActiveCampaignValidator;
import com.egovernment.accesscontrol.domain.entity.Address;
import com.egovernment.accesscontrol.domain.dto.CampaignFilteredDTO;
import com.egovernment.accesscontrol.filter.CampaignRegionFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignService.class);
    private final CampaignRepository campaignRepository;
    private final ModelMapper modelMapper;
    private final KeyService keyService;

    @Transactional
    public List<CampaignFilteredDTO> getActiveLocalCampaigns() {

        try {
            Address address = keyService.extractAddress();

            return this.campaignRepository
                    .findAll()
                    .stream()
                    .filter(ActiveCampaignValidator::isCampaignActive)
                    .filter(c -> CampaignRegionFilter.filterByRegion(c, address))
                    .map(c -> this.modelMapper.map(c, CampaignFilteredDTO.class))
                    .collect(Collectors.toList());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | JsonProcessingException e) {
            LOGGER.error("Error processing campaign data: ", e);
            return Collections.emptyList();
        }
    }

}
