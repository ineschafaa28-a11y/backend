package com.Back.BackEnd.controller;

import com.Back.BackEnd.model.Flight;
import com.Back.BackEnd.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Ajouter un vol
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.addFlight(flight);
        return ResponseEntity.ok(newFlight);
    }

    // Récupérer tous les vols
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // Récupérer un vol par ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer un vol par numéro
    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByNumber(flightNumber);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        }
        return ResponseEntity.notFound().build();
    }

    // Mettre à jour le statut d’un vol
    @PutMapping("/{id}/status")
    public ResponseEntity<Flight> updateFlightStatus(@PathVariable Long id,
                                                     @RequestParam String status) {
        try {
            Flight updatedFlight = flightService.updateFlightStatus(id, status);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un vol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
