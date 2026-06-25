package ma.projet.parcinformatique.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.TypeMateriel;
import ma.projet.parcinformatique.service.MaterielService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materiels")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MaterielController {

    private final MaterielService materielService;

    @GetMapping
    public ResponseEntity<List<Materiel>> getMateriels(
            @RequestParam(required = false) TypeMateriel type,
            @RequestParam(required = false) EtatMateriel etat) {
        if (type != null && etat != null) {
            return ResponseEntity.ok(materielService.findByTypeAndEtat(type, etat));
        } else if (type != null) {
            return ResponseEntity.ok(materielService.findByType(type));
        } else if (etat != null) {
            return ResponseEntity.ok(materielService.findByEtat(etat));
        }
        return ResponseEntity.ok(materielService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiel> getMaterielById(@PathVariable Long id) {
        return ResponseEntity.ok(materielService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Materiel> createMateriel(@Valid @RequestBody Materiel materiel) {
        Materiel created = materielService.save(materiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materiel> updateMateriel(@PathVariable Long id, @RequestBody Materiel materiel) {
        Materiel updated = materielService.update(id, materiel);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMateriel(@PathVariable Long id) {
        materielService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Le matériel a été supprimé avec succès."));
    }

    @GetMapping("/stats/pannes")
    public ResponseEntity<Map<String, Long>> getStatsPannes() {
        return ResponseEntity.ok(materielService.getPannesByType());
    }

    @GetMapping("/stats/par-type")
    public ResponseEntity<Map<String, Long>> getStatsParType() {
        return ResponseEntity.ok(materielService.getTotalByType());
    }
}
