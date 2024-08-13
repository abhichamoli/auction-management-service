package com.test.auctionservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, KafkaWinMessage> kafkaTemplate;

    public void sendMessage(String topic, KafkaWinMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
