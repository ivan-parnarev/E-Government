package com.egovernment.kafkaproducer.web;

import com.egovernment.kafkaproducer.domain.dto.CampaignsTopicDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.service.KafkaService;
import com.egovernment.kafkaproducer.web.interfaces.KafkaTopicControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaTopicController implements KafkaTopicControllerInterface {

    private final KafkaService kafkaService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<ApiCustomResponse> createCampaignsTopic(CampaignsTopicDTO campaignsTopicDTO) {

        ApiCustomResponse response = kafkaService.createTopics(campaignsTopicDTO);

        return ResponseEntity.ok().body(response);
    }
}
