package com.Back.BackEnd.repository;

import com.Back.BackEnd.model.Flight;
import com.Back.BackEnd.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByStatus(FlightStatus status);

    List<Flight> findByDeparture(String departure);

    List<Flight> findByArrival(String arrival);

    List<Flight> findByDepartureAndArrival(String departure, String arrival);

    boolean existsByFlightNumber(String flightNumber);
}
