package com.egovernment.kafka.web.interfaces;

import com.egovernment.kafka.domain.dto.CampaignsTopicDTO;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.TOPIC_PATH)
public interface KafkaTopicControllerInterface {

    ResponseEntity<ApiCustomResponse> createCampaignsTopic(@RequestBody CampaignsTopicDTO campaignsTopicDTO);

}
