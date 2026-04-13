package com.Back.BackEnd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le numéro du vol est obligatoire")
    @Column(nullable = false, unique = true)
    private String flightNumber;

    public String getFlightNumber(){
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    @NotBlank(message = "Le lieu de départ est obligatoire")
    @Column(nullable = false)
    private String departure;

    @NotBlank(message = "Le lieu d’arrivée est obligatoire")
    @Column(nullable = false)
    private String arrival;

    @NotNull(message = "L’heure de départ est obligatoire")
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "L’heure d’arrivée est obligatoire")
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @NotNull(message = "Le statut du vol est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    public FlightStatus getStatus() {
        return status;
    }
    public void setStatus(FlightStatus status){
        this.status = status;

    }


    private String gate;
    public String getGate(){
        return gate;
    }
    public void setGate(String gate){
        this.gate = gate ;
    }

    private String terminal;
    public String getTerminal(){
        return terminal ;
    }
    public void setTerminal(String terminal){
        this.terminal = terminal;
    }
}