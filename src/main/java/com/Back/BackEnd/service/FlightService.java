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
    public FlightResponseDTO  getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec l'id : " + id));
        return mapToResponseDTO(flight);
    }

    // Récupérer un vol par son numéro
    public FlightResponseDTO getFlightByNumber(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec le numéro : " + flightNumber));
        return mapToResponseDTO(flight);
    }

    // Rechercher des vols
    public List<FlightResponseDTO> searchFlights(String departure, String arrival, FlightStatus status) {
        List<Flight> flights;

        if (departure != null && !departure.isBlank() && arrival != null && !arrival.isBlank()) {
            flights = flightRepository.findByDepartureAndArrival(departure, arrival);
        } else if (departure != null && !departure.isBlank()) {
            flights = flightRepository.findByDeparture(departure);
        } else if (arrival != null && !arrival.isBlank()) {
            flights = flightRepository.findByArrival(arrival);
        } else if (status != null) {
            flights = flightRepository.findByStatus(status);
        } else {
            flights = flightRepository.findAll();
        }

        return flights.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Mettre à jour complètement un vol
    public FlightResponseDTO updateFlight(Long id, FlightRequestDTO dto) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec l'id :" + id));
       


        existingFlight.setFlightNumber(dto.getFlightNumber());
        existingFlight.setDeparture(dto.getDeparture());
        existingFlight.setArrival(dto.getArrival());
        existingFlight.setDepartureTime(dto.getDepartureTime());
        existingFlight.setArrivalTime(dto.getArrivalTime());
        existingFlight.setStatus(dto.getStatus());
        existingFlight.setGate(dto.getGate());
        existingFlight.setTerminal(dto.getTerminal());

        validateFlight(existingFlight);

        Flight flightWithSameNumber = flightRepository.findByFlightNumber(dto.getFlightNumber())
                .orElse(null);

        if (flightWithSameNumber != null && !flightWithSameNumber.getId().equals(id)) {
            throw new IllegalArgumentException("Un autre vol utilise déjà ce numéro");
        }

        Flight updated = flightRepository.save(existingFlight);
        return mapToResponseDTO(updated);
    }

    // Mettre à jour seulement le statut
    public FlightResponseDTO  updateFlightStatus(Long id, FlightStatus status) {
        Flight flight = flightRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec l'id :" + id));
        flight.setStatus(status);

        Flight updated = flightRepository.save(flight);
        return mapToResponseDTO(updated);
    }

    // Supprimer un vol
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Vol introuvable avec l'id :" + id));
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
