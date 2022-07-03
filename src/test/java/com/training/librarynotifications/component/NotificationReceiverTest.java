package com.training.librarynotifications.component;

import com.training.librarynotifications.entity.Notification;
import com.training.librarynotifications.repository.NotificationRepository;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NotificationReceiverTest {

    @Autowired
    private NotificationReceiver notificationReceiver;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void testReceiver_Success() {
        JSONObject root = new JSONObject();
        root.put("accountId", "abc123");
        root.put("message", "Random Message");

        notificationReceiver.receiver(root.toJSONString());

        List<Notification> notifications = notificationRepository.findAll();

        assertEquals(1, notifications.size());
        assertEquals("Random Message", notifications.get(0).getMessage());
    }
}
