package com.egovernment.kafka.service;

import com.egovernment.kafka.factory.KafkaListenerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaListenerCreationService {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaListenerContainerFactory kafkaListenerContainerFactory;

    public void createAndRegisterListener(String topic) {
        KafkaListenerEndpoint listener = KafkaListenerFactory.createKafkaListenerEndpoint(topic);
        kafkaListenerEndpointRegistry.registerListenerContainer(listener, kafkaListenerContainerFactory, true);
    }

}
