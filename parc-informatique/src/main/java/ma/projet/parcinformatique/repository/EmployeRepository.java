package ma.projet.parcinformatique.repository;

import ma.projet.parcinformatique.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByService(String service);

    boolean existsByEmail(String email);
}
