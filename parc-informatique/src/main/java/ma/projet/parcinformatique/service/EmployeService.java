package ma.projet.parcinformatique.service;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Employe;
import ma.projet.parcinformatique.repository.EmployeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;

    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employé introuvable avec l'id : " + id));
    }

    public Employe save(Employe employe) {
        if (employeRepository.existsByEmail(employe.getEmail())) {
            throw new IllegalArgumentException("Un employé avec l'adresse email '" + employe.getEmail() + "' existe déjà");
        }
        return employeRepository.save(employe);
    }

    public Employe update(Long id, Employe details) {
        Employe existing = findById(id);

        if (details.getEmail() != null && !details.getEmail().isBlank() && !details.getEmail().equals(existing.getEmail())) {
            if (employeRepository.existsByEmail(details.getEmail())) {
                throw new IllegalArgumentException("Un employé avec l'adresse email '" + details.getEmail() + "' existe déjà");
            }
            existing.setEmail(details.getEmail());
        }

        if (details.getNom() != null && !details.getNom().isBlank()) {
            existing.setNom(details.getNom());
        }

        if (details.getService() != null && !details.getService().isBlank()) {
            existing.setService(details.getService());
        }

        return employeRepository.save(existing);
    }

    public void delete(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new NoSuchElementException("Employé introuvable avec l'id : " + id);
        }
        employeRepository.deleteById(id);
    }

    public List<Employe> findByService(String service) {
        return employeRepository.findByService(service);
    }
}
