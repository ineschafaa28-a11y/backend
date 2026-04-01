package com.Back.BackEnd.controller;

import com.Back.BackEnd.model.Notification;
import com.Back.BackEnd.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Créer une notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification newNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(newNotification);
    }

    // Récupérer toutes les notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    // Récupérer une notification par ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer les notifications d’un utilisateur
    @GetMapping("/user/{recipientId}")
    public ResponseEntity<List<Notification>> getNotificationsByRecipient(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationService.getNotificationsByRecipient(recipientId));
    }

    // Récupérer les notifications liées à un bagage
    @GetMapping("/baggage/{baggageId}")
    public ResponseEntity<List<Notification>> getNotificationsByBaggage(@PathVariable Long baggageId) {
        return ResponseEntity.ok(notificationService.getNotificationsByBaggage(baggageId));
    }

    // Récupérer les notifications liées à un vol
    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Notification>> getNotificationsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(notificationService.getNotificationsByFlight(flightId));
    }

    // Supprimer une notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
