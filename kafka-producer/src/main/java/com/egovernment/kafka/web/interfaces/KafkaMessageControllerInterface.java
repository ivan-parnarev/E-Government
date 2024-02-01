package com.egovernment.kafka.web.interfaces;

import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.MESSAGE_PATH)
public interface KafkaMessageControllerInterface {
    ResponseEntity<ApiCustomResponse> sendMessage(@RequestBody UserVotedInfoDTO userVotedInfoDTO);
}
