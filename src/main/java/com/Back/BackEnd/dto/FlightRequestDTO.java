package com.Back.BackEnd.dto;

import com.Back.BackEnd.model.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class FlightRequestDTO {

    @NotBlank(message = "Le numéro du vol est obligatoire")
    private String flightNumber;

    @NotBlank(message = "Le lieu de départ est obligatoire")
    private String departure;

    @NotBlank(message = "Le lieu d’arrivée est obligatoire")
    private String arrival;

    @NotNull(message = "L’heure de départ est obligatoire")
    private LocalDateTime departureTime;

    @NotNull(message = "L’heure d’arrivée est obligatoire")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Le statut du vol est obligatoire")
    private FlightStatus status;

    private String gate;
    private String terminal;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
