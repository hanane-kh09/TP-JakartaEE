package ma.projet.parcinformatique.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Employe;
import ma.projet.parcinformatique.service.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;

    @GetMapping
    public ResponseEntity<List<Employe>> getEmployes(@RequestParam(required = false) String service) {
        if (service != null && !service.isBlank()) {
            return ResponseEntity.ok(employeService.findByService(service));
        }
        return ResponseEntity.ok(employeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employe> createEmploye(@Valid @RequestBody Employe employe) {
        Employe created = employeService.save(employe);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        Employe updated = employeService.update(id, employe);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmploye(@PathVariable Long id) {
        employeService.delete(id);
        return ResponseEntity.ok(Map.of("message", "L'employé a été supprimé avec succès."));
    }
}
