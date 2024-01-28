package com.egovernment.kafka.listener;

import com.egovernment.kafka.consumer.MessageCatalog;
import com.egovernment.kafka.domain.dto.UserVotedInfoDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class KafkaTemplateListener implements MessageListener<String, UserVotedInfoDTO> {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaTemplateListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, UserVotedInfoDTO> data) {
        LOGGER.info("RECORD PROCESSING: {} WITH KEY {} IN TOPIC {}", data.value(), data.key(), data.topic());
        MessageCatalog.addVoteToTopic(data.topic(), data.value());
    }

}
