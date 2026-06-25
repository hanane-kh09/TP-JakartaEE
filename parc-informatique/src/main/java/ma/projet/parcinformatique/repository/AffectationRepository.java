package ma.projet.parcinformatique.repository;

import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.enums.StatutAffectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    List<Affectation> findByStatut(StatutAffectation statut);

    List<Affectation> findByEmployeService(String service);

    List<Affectation> findByStatutAndEmployeService(StatutAffectation statut, String service);

    Optional<Affectation> findByMaterielIdAndStatut(Long materielId, StatutAffectation statut);

    List<Affectation> findByEmployeId(Long employeId);

    List<Affectation> findByMaterielId(Long materielId);

    @Query("SELECT COUNT(a) > 0 FROM Affectation a WHERE a.materiel.id = :materielId AND a.statut = ma.projet.parcinformatique.enums.StatutAffectation.ACTIVE")
    boolean existsAffectationActiveForMateriel(@Param("materielId") Long materielId);

    @Query("SELECT COUNT(DISTINCT a.materiel.id) FROM Affectation a WHERE a.statut = ma.projet.parcinformatique.enums.StatutAffectation.ACTIVE")
    long countMaterielActifementAffectes();
}
