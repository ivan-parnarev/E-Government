package com.egovernment.kafka.web.interfaces;

import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.MESSAGES_PATH)
public interface KafkaMessageControllerInterface {

    ResponseEntity<List<UserVotedInfoDTO>> getCampaignResults();

}
