package com.egovernment.kafka.service;

import com.egovernment.kafka.domain.dto.CampaignFilteredDTO;
import com.egovernment.main.domain.response.ApiCustomResponse;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KafkaMessageServiceTest {
    @Mock
    private KafkaTemplate kafkaTemplate;
    @InjectMocks
    private KafkaMessageService kafkaMessageService;
    private final int OFFSET = 123;
    private final String SUCCESSFULLY_SEND_EXPECTED_MESSAGE_SUBSTRING = "Successfully send message with key";
    private final String FAILED_SEND_EXPECTED_MESSAGE_SUBSTRING = "Unable to send message with key";
    @Test
    public void kafkaMessageServiceSendsMessagesCorrectly() throws InterruptedException {
        TopicPartition topicPartition = new TopicPartition("campaigns", 1);

        RecordMetadata recordMetadata = new RecordMetadata(topicPartition, 0,
                OFFSET, System.currentTimeMillis(),
                -1, -1);
        SendResult<String, String> sendResult = new SendResult<>(null, recordMetadata);
        CompletableFuture<SendResult<String, String>> completedFuture =
                CompletableFuture.completedFuture(sendResult);

        when(kafkaTemplate.send(anyString(), anyString(), any())).thenReturn(completedFuture);
        CampaignFilteredDTO message = CampaignFilteredDTO.builder().campaignTitle("TEST_CAMPAIGN_TITLE").build();

        ApiCustomResponse response = kafkaMessageService.sendMessage(message);
        Thread.sleep(100);
        assertNotNull(response);
        assertTrue(response.getMessage().contains(SUCCESSFULLY_SEND_EXPECTED_MESSAGE_SUBSTRING));
    }

    @Test
    public void kafkaMessageServiceExpectsException() throws InterruptedException {
        CompletableFuture<SendResult<String, String>> exceptionallyCompletedFuture =
                CompletableFuture.failedFuture(new KafkaException("Mock Kafka exception"));

        when(kafkaTemplate.send(anyString(), anyString(), any()))
                .thenReturn(exceptionallyCompletedFuture);

        CampaignFilteredDTO message = CampaignFilteredDTO.builder().campaignTitle("TEST_CAMPAIGN_TITLE").build();

        ApiCustomResponse response = kafkaMessageService.sendMessage(message);
        Thread.sleep(100);
        assertNotNull(response);
        assertTrue(response.getMessage().contains(FAILED_SEND_EXPECTED_MESSAGE_SUBSTRING));
    }

}
