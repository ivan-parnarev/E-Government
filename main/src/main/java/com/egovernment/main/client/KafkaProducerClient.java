package com.egovernment.main.client;

import com.egovernment.main.domain.dto.voteCampaign.UserVotedInfoDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;
import com.egovernment.main.web.path.ApiPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kafka-producer-client", url = "http://localhost:9191/api/v1")
public interface KafkaProducerClient {

    @PostMapping(ApiPaths.MESSAGE_PATH + ApiPaths.SEND_PATH)
    ResponseEntity<ApiCustomResponse> sendMessage(@RequestBody UserVotedInfoDTO userVotedInfoDTO);

}
