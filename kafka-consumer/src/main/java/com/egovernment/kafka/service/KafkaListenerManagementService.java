package com.egovernment.kafka.service;

import com.egovernment.kafka.factory.KafkaListenerFactory;
import com.egovernment.kafka.web.KafkaListenerController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListenerManagementService {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerController.class);


    public void stopListener(String topic) {
        String listenerId = KafkaListenerFactory.KAFKA_LISTENER_ID + topic;
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);

        if (listenerContainer != null && listenerContainer.isRunning()) {
            listenerContainer.stop();
            LOGGER.info("Listener {} stopped", listenerId);
        } else if (listenerContainer != null) {
            LOGGER.info("Listener {} is already stopped.", listenerId);
        } else {
            LOGGER.info("Listener {} not found.", listenerId);
        }
    }

    public void startListener(String topic){
        String listenerId = KafkaListenerFactory.KAFKA_LISTENER_ID + topic;

        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry
                .getListenerContainer(listenerId);

        if(listenerContainer != null && !listenerContainer.isRunning()){
            listenerContainer.start();
            LOGGER.info("Listener {} stopped", listenerId);
        } else if (listenerContainer != null) {
            LOGGER.info("Listener {} is already stopped.", listenerId);
        } else {
            LOGGER.info("Listener {} not found.", listenerId);
        }
    }

}
