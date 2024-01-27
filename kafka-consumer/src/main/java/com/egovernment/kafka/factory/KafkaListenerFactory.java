package com.egovernment.kafka.factory;

import com.egovernment.kafka.listener.KafkaTemplateListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.concurrent.atomic.AtomicLong;

public class KafkaListenerFactory {

    private static String KAFKA_GROUP_ID = "kafkaGroupId";
    private static String KAFKA_LISTENER_ID = "eGovKafkaListenerId-";
    private static AtomicLong endpointIdIndex = new AtomicLong(1);


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
        kafkaListenerEndpoint.setId(generateListenerId());
        kafkaListenerEndpoint.setGroupId(KAFKA_GROUP_ID);
        kafkaListenerEndpoint.setAutoStartup(true);
        kafkaListenerEndpoint.setTopics(topic);
        kafkaListenerEndpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        return kafkaListenerEndpoint;
    }

    private static String generateListenerId() {
        return KAFKA_LISTENER_ID + endpointIdIndex.getAndIncrement();
    }

}
