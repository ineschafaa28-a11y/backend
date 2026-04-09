package com.Back.BackEnd.service;

import com.Back.BackEnd.model.Flight;
import com.Back.BackEnd.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.Back.BackEnd.model.FlightStatus;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Ajouter un nouveau vol
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Récupérer tous les vols
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Récupérer un vol par son ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Récupérer un vol par son numéro
    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable"));
    }

    // Mettre à jour le statut d’un vol
    public Flight updateFlightStatus(Long id, FlightStatus status) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable"));
        flight.setStatus(status);
        return flightRepository.save(flight);
    }

    // Supprimer un vol
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
