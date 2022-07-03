package com.training.librarynotifications.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.librarynotifications.entity.Notification;
import com.training.librarynotifications.entity.dto.NotificationDTO;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationsMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static List<NotificationDTO> toDTOs(List<Notification> notifications) {
        return notifications
            .stream()
            .map(n -> mapper.convertValue(n, NotificationDTO.class))
            .collect(Collectors.toList());
    }
}
