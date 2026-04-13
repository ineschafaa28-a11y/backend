package com.Back.BackEnd.controller;

import com.Back.BackEnd.dto.FlightRequestDTO;
import com.Back.BackEnd.dto.FlightResponseDTO;
import com.Back.BackEnd.model.Flight;
import com.Back.BackEnd.model.FlightStatus;
import com.Back.BackEnd.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDTO> addFlight(@Valid @RequestBody FlightRequestDTO dto) {
        return ResponseEntity.ok(flightService.addFlight(dto));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String arrival,
            @RequestParam(required = false) FlightStatus status
    ) {
        return ResponseEntity.ok(flightService.searchFlights(departure, arrival, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByNumber(flightNumber);
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @Valid @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Flight> updateFlightStatus(@PathVariable Long id,
                                                     @RequestParam FlightStatus status) {
        Flight updatedFlight = flightService.updateFlightStatus(id, status);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}