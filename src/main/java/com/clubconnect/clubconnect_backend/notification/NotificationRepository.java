package com.clubconnect.clubconnect_backend.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find notifications by user ID
    List<Notification> findByUserId(Long userId);

    // Find notifications by tag
    List<Notification> findByTag(String tag);
}
