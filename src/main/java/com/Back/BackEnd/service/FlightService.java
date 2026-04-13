package com.Back.BackEnd.service;

import com.Back.BackEnd.model.Flight;
import com.Back.BackEnd.dto.FlightRequestDTO;
import com.Back.BackEnd.dto.FlightResponseDTO;
import com.Back.BackEnd.model.FlightStatus;
import com.Back.BackEnd.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Ajouter un nouveau vol
    public FlightResponseDTO addFlight(FlightRequestDTO dto) {

        Flight flight = new Flight();

        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDeparture(dto.getDeparture());
        flight.setArrival(dto.getArrival());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setStatus(dto.getStatus());
        flight.setGate(dto.getGate());
        flight.setTerminal(dto.getTerminal());

        Flight savedFlight = flightRepository.save(flight);

        // convertir ENTITY → DTO
        FlightResponseDTO response = new FlightResponseDTO();

        response.setId(savedFlight.getId());
        response.setFlightNumber(savedFlight.getFlightNumber());
        response.setDeparture(savedFlight.getDeparture());
        response.setArrival(savedFlight.getArrival());
        response.setDepartureTime(savedFlight.getDepartureTime());
        response.setArrivalTime(savedFlight.getArrivalTime());
        response.setStatus(savedFlight.getStatus());
        response.setGate(savedFlight.getGate());
        response.setTerminal(savedFlight.getTerminal());

        return response;
    }

    // Récupérer tous les vols
    public List<FlightResponseDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Récupérer un vol par son ID
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec l'id : " + id));
    }

    // Récupérer un vol par son numéro
    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec le numéro : " + flightNumber));
    }

    // Rechercher des vols
    public List<Flight> searchFlights(String departure, String arrival, FlightStatus status) {
        if (departure != null && !departure.isBlank() && arrival != null && !arrival.isBlank()) {
            return flightRepository.findByDepartureAndArrival(departure, arrival);
        }

        if (departure != null && !departure.isBlank()) {
            return flightRepository.findByDeparture(departure);
        }

        if (arrival != null && !arrival.isBlank()) {
            return flightRepository.findByArrival(arrival);
        }

        if (status != null) {
            return flightRepository.findByStatus(status);
        }

        return flightRepository.findAll();
    }

    // Mettre à jour complètement un vol
    public Flight updateFlight(Long id, Flight updatedFlight) {
        Flight existingFlight = getFlightById(id);

        existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
        existingFlight.setDeparture(updatedFlight.getDeparture());
        existingFlight.setArrival(updatedFlight.getArrival());
        existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
        existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
        existingFlight.setStatus(updatedFlight.getStatus());
        existingFlight.setGate(updatedFlight.getGate());
        existingFlight.setTerminal(updatedFlight.getTerminal());

        validateFlight(existingFlight);

        Flight flightWithSameNumber = flightRepository.findByFlightNumber(updatedFlight.getFlightNumber())
                .orElse(null);

        if (flightWithSameNumber != null && !flightWithSameNumber.getId().equals(id)) {
            throw new IllegalArgumentException("Un autre vol utilise déjà ce numéro");
        }

        return flightRepository.save(existingFlight);
    }

    // Mettre à jour seulement le statut
    public Flight updateFlightStatus(Long id, FlightStatus status) {
        Flight flight = getFlightById(id);
        flight.setStatus(status);
        return flightRepository.save(flight);
    }

    // Supprimer un vol
    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

    // Validation métier
    private void validateFlight(Flight flight) {
        if (flight.getFlightNumber() == null || flight.getFlightNumber().isBlank()) {
            throw new IllegalArgumentException("Le numéro de vol est obligatoire");
        }

        if (flight.getDeparture() == null || flight.getDeparture().isBlank()) {
            throw new IllegalArgumentException("Le lieu de départ est obligatoire");
        }

        if (flight.getArrival() == null || flight.getArrival().isBlank()) {
            throw new IllegalArgumentException("Le lieu d’arrivée est obligatoire");
        }

        if (flight.getDepartureTime() == null) {
            throw new IllegalArgumentException("L’heure de départ est obligatoire");
        }

        if (flight.getArrivalTime() == null) {
            throw new IllegalArgumentException("L’heure d’arrivée est obligatoire");
        }

        if (flight.getStatus() == null) {
            throw new IllegalArgumentException("Le statut du vol est obligatoire");
        }

        if (flight.getArrivalTime().isBefore(flight.getDepartureTime())) {
            throw new IllegalArgumentException("L’heure d’arrivée ne peut pas être avant l’heure de départ");
        }
    }
    private FlightResponseDTO mapToResponseDTO(Flight flight) {
        FlightResponseDTO dto = new FlightResponseDTO();

        dto.setId(flight.getId());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDeparture(flight.getDeparture());
        dto.setArrival(flight.getArrival());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setStatus(flight.getStatus());
        dto.setGate(flight.getGate());
        dto.setTerminal(flight.getTerminal());

        return dto;
    }
}
