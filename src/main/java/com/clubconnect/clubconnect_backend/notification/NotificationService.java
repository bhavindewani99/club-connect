package com.clubconnect.clubconnect_backend.notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(NotificationDTO notificationDTO);
    List<Notification> getNotificationsByUserId(Long userId);
    List<Notification> getNotificationsByTag(String tag);
}
