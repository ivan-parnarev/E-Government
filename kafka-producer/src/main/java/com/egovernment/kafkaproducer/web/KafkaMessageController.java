package com.egovernment.kafkaproducer.web;

import com.egovernment.kafkaproducer.domain.entity.UserVotedInfoDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.service.KafkaService;
import com.egovernment.kafkaproducer.web.interfaces.KafkaMessageControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaMessageController implements KafkaMessageControllerInterface {

    private final KafkaService kafkaSendMessageService;

    @Override
    @PostMapping("/send")
    public ResponseEntity<ApiCustomResponse> sendMessage
            (@RequestBody UserVotedInfoDTO userVotedInfoDTO){

        ApiCustomResponse response = kafkaSendMessageService
                .sendMessage(userVotedInfoDTO, userVotedInfoDTO.getCampaignTitle());

        return ResponseEntity
                .ok()
                .body(response);

    }

}
