package com.Back.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // clé primaire auto-incrémentée
    private Long id;

    @Column(nullable = false, unique = true)
    private String barcode;   // Code-barres unique pour identifier le bagage

    @Column(nullable = false)
    private double weight;    // Poids du bagage

    @Column(nullable = false)
    private String status;    // Statut du bagage (checked-in, loaded, arrived, ready-to-pickup)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;       // Propriétaire du bagage (relation avec User)

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;    // Vol associé au bagage
}
