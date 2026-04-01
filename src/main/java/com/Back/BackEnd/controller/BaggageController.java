package com.Back.BackEnd.controller;

import com.Back.BackEnd.model.Baggage;
import com.Back.BackEnd.service.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    @Autowired
    private BaggageService baggageService;

    // Ajouter un bagage
    @PostMapping
    public ResponseEntity<Baggage> addBaggage(@RequestBody Baggage baggage) {
        Baggage newBaggage = baggageService.addBaggage(baggage);
        return ResponseEntity.ok(newBaggage);
    }

    // Récupérer tous les bagages
    @GetMapping
    public ResponseEntity<List<Baggage>> getAllBaggage() {
        return ResponseEntity.ok(baggageService.getAllBaggage());
    }

    // Récupérer un bagage par ID
    @GetMapping("/{id}")
    public ResponseEntity<Baggage> getBaggageById(@PathVariable Long id) {
        Optional<Baggage> baggage = baggageService.getBaggageById(id);
        return baggage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer un bagage par code-barres
    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<Baggage> getBaggageByBarcode(@PathVariable String barcode) {
        Baggage baggage = baggageService.getBaggageByBarcode(barcode);
        if (baggage != null) {
            return ResponseEntity.ok(baggage);
        }
        return ResponseEntity.notFound().build();
    }

    // Récupérer les bagages d’un utilisateur
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Baggage>> getBaggageByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(baggageService.getBaggageByOwner(ownerId));
    }

    // Mettre à jour le statut d’un bagage
    @PutMapping("/{id}/status")
    public ResponseEntity<Baggage> updateBaggageStatus(@PathVariable Long id,
                                                       @RequestParam String status) {
        try {
            Baggage updatedBaggage = baggageService.updateBaggageStatus(id, status);
            return ResponseEntity.ok(updatedBaggage);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un bagage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaggage(@PathVariable Long id) {
        baggageService.getBaggageById(id).ifPresentOrElse(
                b -> baggageService.updateBaggageStatus(id, "deleted"),
                () -> { throw new RuntimeException("Bagage introuvable !"); }
        );
        return ResponseEntity.noContent().build();
    }
}
