package com.egovernment.kafka.web.interfaces;

import com.egovernment.kafka.domain.dto.ListenerTopicDto;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPaths.BASE_API_PATH + ApiPaths.LISTENER_PATH)
public interface KafkaListenerControllerInterface {

    ResponseEntity<ApiCustomResponse> createTopicListener(@RequestBody ListenerTopicDto topic);

}
