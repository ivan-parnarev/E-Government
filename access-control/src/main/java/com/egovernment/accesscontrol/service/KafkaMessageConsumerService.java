package com.egovernment.accesscontrol.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumerService {
    @KafkaListener(topics = "campaigns", groupId = "eGovConsumerGroupAccessControl")
    public void listenGroup(String message) {
        System.out.println("Received Message in group : " + message);
    }
}
