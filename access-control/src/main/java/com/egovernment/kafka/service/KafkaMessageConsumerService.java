package com.egovernment.kafka.service;

import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumerService {
    @KafkaListener(topics = "campaigns", groupId = "eGovConsumerGroupAccessControl")
        public void listenGroup(CampaignFilteredDTO message) {
        System.out.println("Received Message in group : " + message);
    }
}
