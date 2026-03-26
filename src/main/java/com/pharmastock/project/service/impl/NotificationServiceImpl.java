package com.pharmastock.project.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmastock.project.dto.NotificationDTO;
import com.pharmastock.project.entity.Notification;
import com.pharmastock.project.entity.enums.NotificationCategory;
import com.pharmastock.project.entity.enums.NotificationStatus;
import com.pharmastock.project.repository.NotificationRepository;
import com.pharmastock.project.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void createNotification(Long userId, String message, NotificationCategory category) {
        Notification notification = Notification.builder()
                .userId(userId)
                .message(message)
                .category(category)
                .status(NotificationStatus.UNREAD)
                .createdDate(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD).stream()
                .map(n -> NotificationDTO.builder()
                        .notificationId(n.getNotificationId())
                        .userId(n.getUserId())
                        .message(n.getMessage())
                        .category(n.getCategory())
                        .status(n.getStatus())
                        .createdDate(n.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }

    @Override
    public void dismissNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setStatus(NotificationStatus.DISMISSED);
        notificationRepository.save(notification);
    }
}
