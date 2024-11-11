package com.clubconnect.clubconnect_backend.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clubconnect.clubconnect_backend.event.Event;
import com.clubconnect.clubconnect_backend.event.EventRepository;
import com.clubconnect.clubconnect_backend.exception.ResourceNotFoundException;
import com.clubconnect.clubconnect_backend.user.User;
import com.clubconnect.clubconnect_backend.user.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", notificationDTO.getUserId()));
        
        Event event = null;
        if (notificationDTO.getEventId() != null) {
            event = eventRepository.findById(notificationDTO.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Event", "id", notificationDTO.getEventId()));
        }
        
        Notification notification = new Notification(
                notificationDTO.getMessage(),
                LocalDateTime.now(),
                user,
                event,
                notificationDTO.getTag()
        );
        
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getNotificationsByTag(String tag) {
        return notificationRepository.findByTag(tag);
    }
}
