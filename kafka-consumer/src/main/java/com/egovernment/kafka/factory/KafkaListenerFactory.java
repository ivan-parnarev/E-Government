package com.egovernment.kafka.factory;

import com.egovernment.kafka.listener.KafkaTemplateListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


public class KafkaListenerFactory {

    private static String KAFKA_GROUP_ID = "kafkaGroupId";
    public static String KAFKA_LISTENER_ID = "eGovKafkaListenerId-";
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaListenerFactory.class);

    public static KafkaListenerEndpoint createKafkaListenerEndpoint(String topic) {
        MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint =
                createDefaultMethodKafkaListenerEndpoint(topic);
        kafkaListenerEndpoint.setBean(new KafkaTemplateListener());

        try {
            kafkaListenerEndpoint.setMethod(KafkaTemplateListener.class.getMethod("onMessage", ConsumerRecord.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Attempt to call a non-existent method " + e);
        }
        return kafkaListenerEndpoint;
    }

    private static MethodKafkaListenerEndpoint<String, String> createDefaultMethodKafkaListenerEndpoint(String topic) {
        MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint = new MethodKafkaListenerEndpoint<>();
        kafkaListenerEndpoint.setId(generateListenerId(topic));
        kafkaListenerEndpoint.setGroupId(KAFKA_GROUP_ID);
        kafkaListenerEndpoint.setAutoStartup(true);
        kafkaListenerEndpoint.setTopics(topic);
        kafkaListenerEndpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        return kafkaListenerEndpoint;
    }

    private static String generateListenerId(String topic) {
        String listenerId = KAFKA_LISTENER_ID + topic;
        LOGGER.info("Generating listener with listener id {}", listenerId);
        return listenerId;
    }

}
