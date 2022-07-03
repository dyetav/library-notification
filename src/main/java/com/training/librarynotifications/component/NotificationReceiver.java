package com.training.librarynotifications.component;

import com.training.librarynotifications.entity.Notification;
import com.training.librarynotifications.service.NotificationService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationReceiver.class);

    @Autowired
    private NotificationService notificationService;

    private static JSONParser jsonParser = new JSONParser();

    @RabbitListener(queues = "${library.notification.queue}")
    public void receiver(String notification) {
        LOG.info("Received message => {}", notification);
        JSONObject jsonMessage = null;
        try {
             jsonMessage = (JSONObject) jsonParser.parse(notification);
        } catch (ParseException e) {
            LOG.error("The received message is not a json message: {}", notification);
        }

        String accountId = (String) jsonMessage.get("accountId");
        String message = (String) jsonMessage.get("message");

        Notification notificationEntity = new Notification();
        notificationEntity.setAccountId(accountId);
        notificationEntity.setMessage(message);
        notificationService.saveNotification(notificationEntity);
    }

}
