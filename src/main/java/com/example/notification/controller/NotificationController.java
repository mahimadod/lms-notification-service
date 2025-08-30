package com.example.notification.controller;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.dto.NotificationResponse;
import com.example.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notify")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Mono<ResponseEntity<NotificationResponse>> createNotification(@RequestBody NotificationRequest request) throws Exception {
        return notificationService.sendNotificationByMail(request);
    }

}
