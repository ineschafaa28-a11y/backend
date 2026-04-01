package com.Back.BackEnd.service;

import com.Back.BackEnd.model.Notification;
import com.Back.BackEnd.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Créer une nouvelle notification
    public Notification createNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now()); // horodatage automatique
        return notificationRepository.save(notification);
    }

    // Récupérer toutes les notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Récupérer une notification par son ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Récupérer les notifications d’un utilisateur
    public List<Notification> getNotificationsByRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    // Récupérer les notifications liées à un bagage
    public List<Notification> getNotificationsByBaggage(Long baggageId) {
        return notificationRepository.findByBaggageId(baggageId);
    }

    // Récupérer les notifications liées à un vol
    public List<Notification> getNotificationsByFlight(Long flightId) {
        return notificationRepository.findByFlightId(flightId);
    }

    // Supprimer une notification
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
