package com.egovernment.kafka.web;

import com.egovernment.kafka.domain.dto.ListenerTopicDto;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.response.message.ApiResponseMessage;
import com.egovernment.kafka.service.KafkaListenerCreationService;
import com.egovernment.kafka.service.KafkaListenerManagementService;
import com.egovernment.kafka.web.interfaces.KafkaListenerControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaListenerController implements KafkaListenerControllerInterface {

    private final KafkaListenerCreationService kafkaListenerCreationService;
    private final KafkaListenerManagementService listenerService;
    @Override
    @PostMapping(path = "/create")
    public ResponseEntity<ApiCustomResponse> createTopicListener(@RequestBody ListenerTopicDto topic) {
        kafkaListenerCreationService.createAndRegisterListener(topic.getTopic());
        ApiCustomResponse apiCustomResponse = ApiCustomResponse
                .builder()
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED_LISTENER)
                .build();
        return ResponseEntity.ok(apiCustomResponse);
    }

    @PostMapping("/{listenerId}/stop")
    public void stopListener(@PathVariable String listenerId) {
        listenerService.stopListener(listenerId);
    }

    @PostMapping("/{listenerId}/start")
    public void startListener(@PathVariable String listenerId) {
        listenerService.startListener(listenerId);
    }

}
