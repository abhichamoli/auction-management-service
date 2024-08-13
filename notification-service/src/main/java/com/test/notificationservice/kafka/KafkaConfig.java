package com.test.notificationservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {

    @Value("${kafka.bootstrapServers}")
    String kafkaBoostrapServer;

    @Value("${kafka.consumerGroup}")
    String kafkaConsumerGroup;

    @Bean
    public ConsumerFactory<String, KafkaWinMessage> consumerFactory() {
        JsonDeserializer<KafkaWinMessage> jsonDeserializer = new JsonDeserializer<>(KafkaWinMessage.class);

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBoostrapServer); // Replace with your Kafka server config
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroup);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, KafkaWinMessage> kafkaListenerContainerFactory() {
        ContainerProperties containerProps = new ContainerProperties("${kafka.topic}");
        return new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProps);
    }
}
