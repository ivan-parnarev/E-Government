package com.egovernment.kafkaproducer.web.interfaces;

import com.egovernment.kafkaproducer.domain.dto.CampaignsTopicDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.TOPIC_PATH)
public interface KafkaTopicControllerInterface {

    ResponseEntity<ApiCustomResponse> createCampaignsTopic(@RequestBody CampaignsTopicDTO campaignsTopicDTO);

}
