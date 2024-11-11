package com.clubconnect.clubconnect_backend.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint to create a notification
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        Notification createdNotification = notificationService.createNotification(notificationDTO);
        NotificationDTO responseDto = convertToDto(createdNotification);
        return ResponseEntity.ok(responseDto);
    }

    // Endpoint to get notifications by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(notificationDTOs);
    }

    // Endpoint to get notifications by tag
    @GetMapping("/tag")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByTag(@RequestParam String tag) {
        List<Notification> notifications = notificationService.getNotificationsByTag(tag);
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(notificationDTOs);
    }

    // Convert Notification entity to NotificationDTO
    private NotificationDTO convertToDto(Notification notification) {
        return new NotificationDTO(
            notification.getId(),
            notification.getMessage(),
            notification.getCreatedAt(),
            notification.getUser().getId(),
            notification.getEvent().getId(),
            notification.getTag()
        );
    }
    
}
