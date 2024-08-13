package com.test.notificationservice.service;

import com.test.notificationservice.kafka.KafkaWinMessage;
import com.test.notificationservice.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceEventProcessor {

    @Autowired
    MailingService mailingService;

    public void processWinEvent(KafkaWinMessage winMessage) {
        String text = String.format(Constants.WIN_EMAIL_TEXT, winMessage.getUserId(), winMessage.getProductName());
        mailingService.sendSimpleEmail(winMessage.getUserId(), Constants.WIN_EMAIL_SUBJECT, text);
    }

}
