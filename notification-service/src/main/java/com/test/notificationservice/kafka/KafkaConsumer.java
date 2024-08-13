package com.test.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.notificationservice.service.NotificationServiceEventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    NotificationServiceEventProcessor notificationServiceEventProcessor;
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumerGroup}")
    public void listen(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("consuming kafka win message: {}", message);
            KafkaWinMessage kafkaWinMessage = mapper.readValue(message, KafkaWinMessage.class);
            notificationServiceEventProcessor.processWinEvent(kafkaWinMessage);
        } catch (Exception exception) {
            log.error("error occured when consuming kafka win message: {}", exception.getMessage());
        }
    }
}
