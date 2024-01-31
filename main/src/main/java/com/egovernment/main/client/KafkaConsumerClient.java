package com.egovernment.main.client;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "kafka-consumer-client", url = "http://localhost:9393/api/v1")
public interface KafkaConsumerClient {

    @GetMapping("/messages/consume")
    ResponseEntity<List<UserVotedInfoDTO>> getCampaignResults();

}
