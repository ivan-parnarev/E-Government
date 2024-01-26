package com.egovernment.kafkaproducer.service;

import com.egovernment.kafkaproducer.domain.dto.CampaignsTopicDTO;
import com.egovernment.kafkaproducer.domain.entity.UserVotedInfoDTO;
import com.egovernment.kafkaproducer.response.ApiCustomResponse;
import com.egovernment.kafkaproducer.response.message.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate kafkaTemplate;
    private final KafkaAdmin kafkaAdmin;
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    private void createTopic(String topicName, int partitions, short replicationFactor) {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());

        NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

        try {

            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
            LOGGER.info("New topic {} is created", topicName);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            LOGGER.error("Thread was interrupted during the topic creation process", e);

        } catch (ExecutionException e) {

            LOGGER.error("Error occurred while creating topic {}: {}", topicName, e.getCause().getMessage());

        } catch (Exception e) {

            LOGGER.error("An unexpected error occurred while creating topic {}: {}", topicName, e.getMessage());

        } finally {
            adminClient.close();
        }

    }

    public ApiCustomResponse sendMessage(UserVotedInfoDTO message, String topic) {
        String messageKey = UUID.randomUUID().toString();
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, messageKey, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message=[{}] with key {} with offset=[{}]",
                        message, messageKey, result.getRecordMetadata().offset());
            } else {
                LOGGER.info("Unable to send message=[{}] due to : {}",
                        message, ex.getMessage());
            }
        });

        return ApiCustomResponse
                .builder()
                .message(ApiResponseMessage.MESSAGE_SUCCESSFULLY_SENT)
                .build();

    }

    public ApiCustomResponse createTopics(CampaignsTopicDTO campaignsTopicDTO) {

        campaignsTopicDTO
                .getCampaignTitles()
                .forEach(t -> this.createTopic(t, 2, (short) 1));

        return ApiCustomResponse
                .builder()
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED_TOPIC)
                .build();
    }
}
