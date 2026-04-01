package com.Back.BackEnd.repository;

import com.Back.BackEnd.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Recherche d’un vol par son numéro
    Flight findByFlightNumber(String flightNumber);

    // Recherche des vols par statut (on-time, delayed, cancelled)
    List<Flight> findByStatus(String status);

    // Recherche des vols par ville/aéroport de départ
    List<Flight> findByDeparture(String departure);

    // Recherche des vols par ville/aéroport d’arrivée
    List<Flight> findByArrival(String arrival);
}
