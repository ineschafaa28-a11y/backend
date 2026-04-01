package com.Back.BackEnd.service;

import com.Back.BackEnd.model.Baggage;
import com.Back.BackEnd.repository.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BaggageService {

    @Autowired
    private BaggageRepository baggageRepository;

    // Ajouter un nouveau bagage avec génération automatique de code-barres
    public Baggage addBaggage(Baggage baggage) {
        baggage.setBarcode(generateBarcode());
        baggage.setStatus("checked-in"); // statut initial
        return baggageRepository.save(baggage);
    }

    // Récupérer tous les bagages
    public List<Baggage> getAllBaggage() {
        return baggageRepository.findAll();
    }

    // Récupérer un bagage par son ID
    public Optional<Baggage> getBaggageById(Long id) {
        return baggageRepository.findById(id);
    }

    // Récupérer un bagage par son code-barres
    public Baggage getBaggageByBarcode(String barcode) {
        return baggageRepository.findByBarcode(barcode);
    }

    // Récupérer les bagages d’un utilisateur
    public List<Baggage> getBaggageByOwner(Long ownerId) {
        return baggageRepository.findByOwnerId(ownerId);
    }

    // Mettre à jour le statut d’un bagage
    public Baggage updateBaggageStatus(Long id, String status) {
        Optional<Baggage> baggageOpt = baggageRepository.findById(id);
        if (baggageOpt.isPresent()) {
            Baggage baggage = baggageOpt.get();
            baggage.setStatus(status);
            return baggageRepository.save(baggage);
        }
        throw new RuntimeException("Bagage introuvable !");
    }

    // Générer un code-barres unique
    private String generateBarcode() {
        return "BG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
