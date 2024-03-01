package com.egovernment.kafka.service;

import com.egovernment.accesscontrol.domain.entity.FilteredCampaign;
import com.egovernment.accesscontrol.service.FilteredCampaignService;
import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaMessageConsumerService {
    private final FilteredCampaignService filteredCampaignService;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumerService.class);

    @KafkaListener(topics = "campaigns", groupId = "eGovConsumerGroupAccessControl")
    public void listenGroup(CampaignFilteredDTO message) {
        FilteredCampaign campaign = this.modelMapper.map(message, FilteredCampaign.class);
        this.filteredCampaignService.saveCampaign(campaign);
        LOGGER.info("Received Message: {} in {}", message, LocalDateTime.now());
    }
}
