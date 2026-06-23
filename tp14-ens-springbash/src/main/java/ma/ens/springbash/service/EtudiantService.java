package ma.ens.springbash.service;

import lombok.RequiredArgsConstructor;
import ma.ens.springbash.model.Etudiant;
import ma.ens.springbash.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final EtudiantRepository repository;

    public List<Etudiant> findAll() {
        return repository.findAll();
    }

    public Optional<Etudiant> findById(Long id) {
        return repository.findById(id);
    }

    public Etudiant save(Etudiant etudiant) {
        return repository.save(etudiant);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
