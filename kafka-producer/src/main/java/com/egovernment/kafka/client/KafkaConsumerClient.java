package com.egovernment.kafka.client;

import com.egovernment.kafka.domain.dto.ListenerTopicDTO;
import com.egovernment.kafka.response.ApiCustomResponse;
import com.egovernment.kafka.web.path.ApiPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "access-control-client",
        url = "${spring.cloud.openfeign.client.config.kafka-consumer-client.url}")
public interface KafkaConsumerClient {
    @GetMapping(ApiPaths.LISTENER_PATH + ApiPaths.CREATE_PATH)
    ResponseEntity<ApiCustomResponse> createTopicListener(@RequestBody ListenerTopicDTO topic);
}
