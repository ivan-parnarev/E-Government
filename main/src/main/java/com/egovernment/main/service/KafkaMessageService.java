package com.egovernment.main.service;

import com.egovernment.main.config.KafkaTopicConfiguration;
import com.egovernment.main.domain.response.ApiCustomResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class KafkaMessageService {

    private final KafkaTemplate kafkaTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaMessageService.class);

    public ApiCustomResponse sendMessage(String message){
        AtomicReference<String> response = new AtomicReference<>("");
        String messageKey = UUID.randomUUID().toString();
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(KafkaTopicConfiguration.CAMPAIGNS_TOPIC_NAME, messageKey, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message=[{}] with key {} with offset=[{}]",
                        message, messageKey, result.getRecordMetadata().offset());
                response.set("Successfully send message with key " + messageKey);
            } else {
                LOGGER.info("Unable to send message=[{}] due to : {}",
                        message, ex.getMessage());
                response.set("Unable to send message with key " + messageKey + " due to " + ex.getMessage());

            }
        });

        return ApiCustomResponse.builder()
                .message(response.get())
                .build();
    }

}
