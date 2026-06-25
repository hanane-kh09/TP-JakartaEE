package ma.projet.parcinformatique.controller;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.enums.StatutAffectation;
import ma.projet.parcinformatique.service.AffectationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/affectations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AffectationController {

    private final AffectationService affectationService;

    @GetMapping
    public ResponseEntity<List<Affectation>> getAffectations(
            @RequestParam(required = false) StatutAffectation statut,
            @RequestParam(required = false) String service) {
        if (statut != null && service != null && !service.isBlank()) {
            return ResponseEntity.ok(affectationService.findByStatutAndService(statut, service));
        } else if (statut != null) {
            return ResponseEntity.ok(affectationService.findByStatut(statut));
        } else if (service != null && !service.isBlank()) {
            return ResponseEntity.ok(affectationService.findByService(service));
        }
        return ResponseEntity.ok(affectationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.findById(id));
    }

    @PostMapping("/affecter")
    public ResponseEntity<Affectation> affecter(
            @RequestParam Long materielId,
            @RequestParam Long employeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut) {
        Affectation created = affectationService.affecter(materielId, employeId, dateDebut);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/cloturer")
    public ResponseEntity<Affectation> cloturerAffectation(@PathVariable Long id) {
        Affectation updated = affectationService.cloturerAffectation(id);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Affectation>> getAffectationsByEmploye(@PathVariable Long employeId) {
        return ResponseEntity.ok(affectationService.findByEmploye(employeId));
    }

    @GetMapping("/materiel/{materielId}")
    public ResponseEntity<List<Affectation>> getAffectationsByMateriel(@PathVariable Long materielId) {
        return ResponseEntity.ok(affectationService.findByMateriel(materielId));
    }

    @GetMapping("/stats/utilisation")
    public ResponseEntity<Map<String, Object>> getTauxUtilisation() {
        return ResponseEntity.ok(affectationService.getTauxUtilisation());
    }
}
