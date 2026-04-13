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
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String arrival,
            @RequestParam(required = false) FlightStatus status
    ) {
        return ResponseEntity.ok(flightService.searchFlights(departure, arrival, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<FlightResponseDTO> getFlightByNumber(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.getFlightByNumber(flightNumber));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> updateFlight(@PathVariable Long id, @Valid @RequestBody FlightRequestDTO dto) {
        return ResponseEntity.ok(flightService.updateFlight(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FlightResponseDTO> updateFlightStatus(@PathVariable Long id,
                                                     @RequestParam FlightStatus status) {

        return ResponseEntity.ok(flightService.updateFlightStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}