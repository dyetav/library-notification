package com.training.librarynotifications.service;

import com.training.librarynotifications.entity.Notification;
import com.training.librarynotifications.entity.dto.NotificationDTO;
import com.training.librarynotifications.repository.NotificationRepository;
import com.training.librarynotifications.utils.NotificationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    public void saveNotification(Notification notificationEntity) {
        notificationRepository.save(notificationEntity);
    }

    public List<NotificationDTO> getNotificationsByAccountId(String accountId) {
        return NotificationsMapper.toDTOs(notificationRepository.findByAccountId(accountId));
    }
}
