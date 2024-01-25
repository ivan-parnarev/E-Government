package com.egovernment.kafkaproducer.web.interfaces;

import com.egovernment.kafkaproducer.domain.entity.UserVotedInfoDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.MESSAGE_PATH)
public interface KafkaMessageControllerInterface {
    ResponseEntity<ApiCustomResponse> sendMessage(@RequestBody UserVotedInfoDTO userVotedInfoDTO);
}
