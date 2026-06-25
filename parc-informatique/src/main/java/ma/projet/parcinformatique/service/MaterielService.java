package ma.projet.parcinformatique.service;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.TypeMateriel;
import ma.projet.parcinformatique.repository.MaterielRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterielService {

    private final MaterielRepository materielRepository;

    public List<Materiel> findAll() {
        return materielRepository.findAll();
    }

    public Materiel findById(Long id) {
        return materielRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Matériel introuvable avec l'id : " + id));
    }

    public Materiel save(Materiel materiel) {
        if (materielRepository.existsByRef(materiel.getRef())) {
            throw new IllegalArgumentException("Un matériel avec la référence '" + materiel.getRef() + "' existe déjà");
        }
        return materielRepository.save(materiel);
    }

    public Materiel update(Long id, Materiel details) {
        Materiel existing = findById(id);

        if (details.getRef() != null && !details.getRef().isBlank() && !details.getRef().equals(existing.getRef())) {
            if (materielRepository.existsByRef(details.getRef())) {
                throw new IllegalArgumentException("Un matériel avec la référence '" + details.getRef() + "' existe déjà");
            }
            existing.setRef(details.getRef());
        }

        if (details.getType() != null) {
            existing.setType(details.getType());
        }

        if (details.getMarque() != null && !details.getMarque().isBlank()) {
            existing.setMarque(details.getMarque());
        }

        if (details.getEtat() != null) {
            existing.setEtat(details.getEtat());
        }

        if (details.getDateAchat() != null) {
            existing.setDateAchat(details.getDateAchat());
        }

        return materielRepository.save(existing);
    }

    public void delete(Long id) {
        if (!materielRepository.existsById(id)) {
            throw new NoSuchElementException("Matériel introuvable avec l'id : " + id);
        }
        materielRepository.deleteById(id);
    }

    public List<Materiel> findByType(TypeMateriel type) {
        return materielRepository.findByType(type);
    }

    public List<Materiel> findByEtat(EtatMateriel etat) {
        return materielRepository.findByEtat(etat);
    }

    public List<Materiel> findByTypeAndEtat(TypeMateriel type, EtatMateriel etat) {
        return materielRepository.findByTypeAndEtat(type, etat);
    }

    public Map<String, Long> getPannesByType() {
        return materielRepository.countPannesByType().stream()
                .collect(Collectors.toMap(
                        row -> ((TypeMateriel) row[0]).name(),
                        row -> (Long) row[1]
                ));
    }

    public Map<String, Long> getTotalByType() {
        return materielRepository.countByType().stream()
                .collect(Collectors.toMap(
                        row -> ((TypeMateriel) row[0]).name(),
                        row -> (Long) row[1]
                ));
    }

    public long getTotalMateriel() {
        return materielRepository.count();
    }
}
