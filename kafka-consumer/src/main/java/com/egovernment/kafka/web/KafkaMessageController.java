package com.egovernment.kafka.web;

import com.egovernment.kafka.consumer.MessageCatalog;
import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import com.egovernment.kafka.service.KafkaListenerManagementService;
import com.egovernment.kafka.web.interfaces.KafkaMessageControllerInterface;
import com.egovernment.kafka.web.path.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KafkaMessageController implements KafkaMessageControllerInterface {

    private final KafkaListenerManagementService kafkaListenerManagementService;

    @Override
    @GetMapping(ApiPaths.CONSUME_PATH)
    public ResponseEntity<List<UserVotedInfoDTO>> getCampaignResults(){
        List<UserVotedInfoDTO> campaignsResult = MessageCatalog.getCampaignsResult();
        return ResponseEntity.ok(campaignsResult);
    }

    @PostMapping("/delete/{topic}")
    public ResponseEntity<Boolean> deleteMessagesTopic(@PathVariable("topic") String topic){
        boolean removeCampaignTopic = MessageCatalog.removeCampaignTopic(topic);
        kafkaListenerManagementService.stopListener(topic);
        return ResponseEntity.ok(removeCampaignTopic);
    }

}
