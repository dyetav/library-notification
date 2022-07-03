package com.training.librarynotifications.controller;

import com.training.librarynotifications.entity.dto.NotificationDTO;
import com.training.librarynotifications.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library-notification/api/notifications")
public class NotificationController {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/ping")
    public String ping() {
        LOG.info("PING called");
        return "pong";
    }

    @GetMapping("/v1/accounts/{accountId}")
    public List<NotificationDTO> getNotificationsByAccountId(@PathVariable("accountId") String accountId) {
        return notificationService.getNotificationsByAccountId(accountId);
    }
}
