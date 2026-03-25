package com.pharmastock.project.service;
import com.pharmastock.project.dto.NotificationDTO;
import com.pharmastock.project.entity.enums.NotificationCategory;

import java.util.List;

public interface NotificationService {
    void createNotification(Long userId, String message, NotificationCategory category);
    List<NotificationDTO> getUserNotifications(Long userId);
    void markAsRead(Long notificationId);
    void dismissNotification(Long notificationId);
}
