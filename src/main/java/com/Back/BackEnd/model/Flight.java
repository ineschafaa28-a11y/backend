package com.Back.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // clé primaire auto-incrémentée
    private Long id;

    @Column(nullable = false, unique = true)
    private String flightNumber;   // Numéro de vol (ex: AH1234)

    @Column(nullable = false)
    private String departure;      // Ville/aéroport de départ

    @Column(nullable = false)
    private String arrival;        // Ville/aéroport d’arrivée

    @Column(nullable = false)
    private String status;         // Statut du vol (on-time, delayed, cancelled)

    private String gate;           // Porte d’embarquement (optionnel)

    private String scheduledTime;  // Heure prévue (optionnel)
}
