package com.Back.BackEnd.repository;

import com.Back.BackEnd.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Recherche des notifications envoyées à un utilisateur
    List<Notification> findByRecipientId(Long recipientId);

    // Recherche des notifications liées à un bagage
    List<Notification> findByBaggageId(Long baggageId);

    // Recherche des notifications liées à un vol
    List<Notification> findByFlightId(Long flightId);
}
