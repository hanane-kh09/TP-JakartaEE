package ma.projet.parcinformatique.service;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.entity.Employe;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.StatutAffectation;
import ma.projet.parcinformatique.repository.AffectationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AffectationService {

    private final AffectationRepository affectationRepository;
    private final MaterielService materielService;
    private final EmployeService employeService;

    public List<Affectation> findAll() {
        return affectationRepository.findAll();
    }

    public Affectation findById(Long id) {
        return affectationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Affectation introuvable avec l'id : " + id));
    }

    public Affectation affecter(Long materielId, Long employeId, LocalDate dateDebut) {
        Materiel materiel = materielService.findById(materielId);
        Employe employe = employeService.findById(employeId);

        if (affectationRepository.existsAffectationActiveForMateriel(materielId)) {
            throw new IllegalStateException("Le matériel avec l'id " + materielId + " est déjà affecté activement");
        }

        LocalDate debut = dateDebut != null ? dateDebut : LocalDate.now();

        Affectation affectation = Affectation.builder()
                .materiel(materiel)
                .employe(employe)
                .dateDebut(debut)
                .statut(StatutAffectation.ACTIVE)
                .build();

        return affectationRepository.save(affectation);
    }

    public Affectation cloturerAffectation(Long id) {
        Affectation affectation = findById(id);

        if (affectation.getStatut() != StatutAffectation.ACTIVE) {
            throw new IllegalStateException("Impossible de clôturer une affectation qui n'est pas active");
        }

        affectation.setStatut(StatutAffectation.RETOURNEE);
        affectation.setDateFin(LocalDate.now());

        return affectationRepository.save(affectation);
    }

    public List<Affectation> findByStatut(StatutAffectation statut) {
        return affectationRepository.findByStatut(statut);
    }

    public List<Affectation> findByService(String service) {
        return affectationRepository.findByEmployeService(service);
    }

    public List<Affectation> findByStatutAndService(StatutAffectation statut, String service) {
        return affectationRepository.findByStatutAndEmployeService(statut, service);
    }

    public List<Affectation> findByEmploye(Long employeId) {
        // Validation of employee existence
        employeService.findById(employeId);
        return affectationRepository.findByEmployeId(employeId);
    }

    public List<Affectation> findByMateriel(Long materielId) {
        // Validation of hardware existence
        materielService.findById(materielId);
        return affectationRepository.findByMaterielId(materielId);
    }

    public Affectation update(Long id, Long materielId, Long employeId, LocalDate dateDebut, LocalDate dateFin, StatutAffectation statut) {
        Affectation existing = findById(id);

        if (!existing.getMateriel().getId().equals(materielId)) {
            if (affectationRepository.existsAffectationActiveForMateriel(materielId)) {
                throw new IllegalStateException("Le matériel sélectionné est déjà affecté activement ailleurs");
            }
            existing.setMateriel(materielService.findById(materielId));
        }

        existing.setEmploye(employeService.findById(employeId));
        existing.setDateDebut(dateDebut != null ? dateDebut : LocalDate.now());
        existing.setDateFin(dateFin);
        existing.setStatut(statut);

        return affectationRepository.save(existing);
    }

    public void delete(Long id) {
        if (!affectationRepository.existsById(id)) {
            throw new NoSuchElementException("Affectation introuvable avec l'id : " + id);
        }
        affectationRepository.deleteById(id);
    }

    public Map<String, Object> getTauxUtilisation() {
        long totalMateriel = materielService.getTotalMateriel();
        long materielAffectes = affectationRepository.countMaterielActifementAffectes();
        long materielDisponibles = totalMateriel - materielAffectes;
        double tauxUtilisation = totalMateriel > 0 ? ((double) materielAffectes * 100.0 / totalMateriel) : 0.0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalMateriel", totalMateriel);
        stats.put("materielAffectes", materielAffectes);
        stats.put("materielDisponibles", materielDisponibles);
        stats.put("tauxUtilisationPourcentage", tauxUtilisation);

        return stats;
    }
}
