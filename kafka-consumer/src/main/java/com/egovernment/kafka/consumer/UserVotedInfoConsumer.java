package com.egovernment.kafka.consumer;

import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class UserVotedInfoConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserVotedInfoConsumer.class);

    @KafkaListener(
            containerFactory = "kafkaListenerContainerFactory",
            topics = "Campaign",
            groupId = "E-Gov-Kafka-Consumer"
    )
    public void readMessage(
            @Header(KafkaHeaders.RECEIVED_KEY) String messageKey,
            UserVotedInfoDTO userVotedInfoDTO
    ) {
        LOGGER.info("We have consumed message {} with key {}", userVotedInfoDTO, messageKey);
    }

}
