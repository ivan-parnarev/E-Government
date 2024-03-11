package com.egovernment.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServerAddress;
    public static final String CAMPAIGNS_TOPIC_NAME = "campaigns";
    private static final int PARTITIONS_COUNT = 2;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic defaultCampaignsTopic(){
        return new NewTopic(CAMPAIGNS_TOPIC_NAME, PARTITIONS_COUNT, (short) 1);
    }

}
