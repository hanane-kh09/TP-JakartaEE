package ma.projet.parcinformatique.repository;

import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.TypeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {

    List<Materiel> findByType(TypeMateriel type);

    List<Materiel> findByEtat(EtatMateriel etat);

    List<Materiel> findByTypeAndEtat(TypeMateriel type, EtatMateriel etat);

    boolean existsByRef(String ref);

    @Query("SELECT m.type, COUNT(m) FROM Materiel m WHERE m.etat = ma.projet.parcinformatique.enums.EtatMateriel.EN_PANNE GROUP BY m.type")
    List<Object[]> countPannesByType();

    @Query("SELECT m.type, COUNT(m) FROM Materiel m GROUP BY m.type")
    List<Object[]> countByType();
}
