package com.egovernment.kafka.web;

import com.egovernment.kafka.consumer.MessageCatalog;
import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import com.egovernment.kafka.web.interfaces.KafkaMessageControllerInterface;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaMessageController implements KafkaMessageControllerInterface {

    @Override
    @GetMapping(ApiPaths.CONSUME_PATH)
    public ResponseEntity<List<UserVotedInfoDTO>> getCampaignResults(){
        List<UserVotedInfoDTO> campaignsResult = MessageCatalog.getCampaignsResult();
        return ResponseEntity.ok(campaignsResult);
    }

}
