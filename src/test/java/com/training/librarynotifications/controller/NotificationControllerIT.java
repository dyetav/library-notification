package com.training.librarynotifications.controller;

import com.training.librarynotifications.entity.Notification;
import com.training.librarynotifications.entity.dto.NotificationDTO;
import com.training.librarynotifications.repository.NotificationRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void testNotificationsByAccountId_Success() {
        createNotification("a123", "First Message");
        createNotification("a123", "Second Message");
        NotificationDTO[] notificationsDTO = RestAssured.given().port(port).pathParam("accountId", "a123")
            .expect().contentType(ContentType.JSON)
            .when().get("/library-notification/api/notifications/v1/accounts/{accountId}")
            .then().assertThat().statusCode(200)
            .extract().as(NotificationDTO[].class);

        List<NotificationDTO> notifications = Arrays.asList(notificationsDTO);
        assertEquals(2, notifications.size());
        Set<String> accounts = notifications.stream().map(NotificationDTO::getAccountId).collect(Collectors.toSet());
        assertEquals(1, accounts.size());
        assertEquals("a123", accounts.stream().findFirst().get());
    }

    private void createNotification(String accountId, String message) {
        Notification notification = new Notification();
        notification.setAccountId(accountId);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
}
