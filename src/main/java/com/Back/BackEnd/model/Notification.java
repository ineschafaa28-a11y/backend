package com.Back.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // clé primaire auto-incrémentée
    private Long id;

    @Column(nullable = false)
    private String type;   // Type de notification (SMS, Email, Push)

    @Column(nullable = false)
    private String message; // Contenu du message

    @Column(nullable = false)
    private LocalDateTime timestamp; // Date et heure d’envoi

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User recipient; // Destinataire de la notification

    @ManyToOne
    @JoinColumn(name = "baggage_id")
    private Baggage baggage; // Notification liée à un bagage (optionnel)

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight; // Notification liée à un vol (optionnel)
}
