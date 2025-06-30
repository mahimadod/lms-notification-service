package com.example.notification.service;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.dto.NotificationResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface NotificationService {
     Mono<ResponseEntity<NotificationResponse>> sendNotificationByMail(NotificationRequest notificationRequest);

}
