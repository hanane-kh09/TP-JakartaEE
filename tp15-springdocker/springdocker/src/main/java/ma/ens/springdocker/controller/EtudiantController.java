package ma.ens.springdocker.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.springdocker.entity.Etudiant;
import ma.ens.springdocker.service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService service;

    @GetMapping
    public List<Etudiant> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Etudiant create(@RequestBody Etudiant etudiant) {
        return service.save(etudiant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> update(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return service.findById(id).map(existing -> {
            etudiant.setId(id);
            return ResponseEntity.ok(service.save(etudiant));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
