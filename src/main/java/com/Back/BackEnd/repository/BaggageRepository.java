package com.Back.BackEnd.repository;

import com.Back.BackEnd.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {

    // Recherche d’un bagage par son code-barres
    Baggage findByBarcode(String barcode);

    // Recherche des bagages appartenant à un utilisateur
    List<Baggage> findByOwnerId(Long ownerId);

    // Recherche des bagages associés à un vol
    List<Baggage> findByFlightId(Long flightId);
}
