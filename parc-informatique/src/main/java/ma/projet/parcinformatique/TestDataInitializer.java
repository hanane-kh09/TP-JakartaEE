package ma.projet.parcinformatique;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.entity.Employe;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.StatutAffectation;
import ma.projet.parcinformatique.enums.TypeMateriel;
import ma.projet.parcinformatique.repository.AffectationRepository;
import ma.projet.parcinformatique.repository.EmployeRepository;
import ma.projet.parcinformatique.repository.MaterielRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final MaterielRepository materielRepository;
    private final EmployeRepository employeRepository;
    private final AffectationRepository affectationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (materielRepository.count() == 0) {
            // 1. Insérer 6 matériels
            Materiel pcDell = Materiel.builder()
                    .ref("REF-PC-DELL")
                    .type(TypeMateriel.PC)
                    .marque("Dell")
                    .etat(EtatMateriel.OK)
                    .dateAchat(LocalDate.of(2023, 1, 15))
                    .build();

            Materiel hplaptop = Materiel.builder()
                    .ref("REF-LAP-HP")
                    .type(TypeMateriel.LAPTOP)
                    .marque("HP")
                    .etat(EtatMateriel.OK)
                    .dateAchat(LocalDate.of(2023, 5, 20))
                    .build();

            Materiel canonImprimante = Materiel.builder()
                    .ref("REF-IMP-CANON")
                    .type(TypeMateriel.IMPRIMANTE)
                    .marque("Canon")
                    .etat(EtatMateriel.EN_PANNE)
                    .dateAchat(LocalDate.of(2022, 9, 10))
                    .build();

            Materiel lenovoPC = Materiel.builder()
                    .ref("REF-PC-LENOVO")
                    .type(TypeMateriel.PC)
                    .marque("Lenovo")
                    .etat(EtatMateriel.OK)
                    .dateAchat(LocalDate.of(2023, 11, 2))
                    .build();

            Materiel hpServeur = Materiel.builder()
                    .ref("REF-SRV-HP")
                    .type(TypeMateriel.SERVEUR)
                    .marque("HP")
                    .etat(EtatMateriel.OK)
                    .dateAchat(LocalDate.of(2021, 6, 30))
                    .build();

            Materiel acerPC = Materiel.builder()
                    .ref("REF-PC-ACER")
                    .type(TypeMateriel.PC)
                    .marque("Acer")
                    .etat(EtatMateriel.EN_PANNE)
                    .dateAchat(LocalDate.of(2022, 12, 12))
                    .build();

            materielRepository.save(pcDell);
            materielRepository.save(hplaptop);
            materielRepository.save(canonImprimante);
            materielRepository.save(lenovoPC);
            materielRepository.save(hpServeur);
            materielRepository.save(acerPC);

            // 2. Insérer 4 employés
            Employe alice = Employe.builder()
                    .nom("Alice Martin")
                    .service("Informatique")
                    .email("alice.martin@entreprise.com")
                    .build();

            Employe bob = Employe.builder()
                    .nom("Bob Durand")
                    .service("Comptabilité")
                    .email("bob.durand@entreprise.com")
                    .build();

            Employe charlie = Employe.builder()
                    .nom("Charlie Dubois")
                    .service("RH")
                    .email("charlie.dubois@entreprise.com")
                    .build();

            Employe david = Employe.builder()
                    .nom("David Moreau")
                    .service("Informatique")
                    .email("david.moreau@entreprise.com")
                    .build();

            employeRepository.save(alice);
            employeRepository.save(bob);
            employeRepository.save(charlie);
            employeRepository.save(david);

            // 3. Insérer 4 affectations (3 ACTIVE, 1 RETOURNEE)
            Affectation aff1 = Affectation.builder()
                    .materiel(pcDell)
                    .employe(alice)
                    .dateDebut(LocalDate.of(2024, 1, 10))
                    .statut(StatutAffectation.ACTIVE)
                    .build();

            Affectation aff2 = Affectation.builder()
                    .materiel(hplaptop)
                    .employe(bob)
                    .dateDebut(LocalDate.of(2024, 2, 15))
                    .statut(StatutAffectation.ACTIVE)
                    .build();

            Affectation aff3 = Affectation.builder()
                    .materiel(lenovoPC)
                    .employe(charlie)
                    .dateDebut(LocalDate.of(2024, 3, 1))
                    .statut(StatutAffectation.ACTIVE)
                    .build();

            Affectation aff4 = Affectation.builder()
                    .materiel(canonImprimante)
                    .employe(david)
                    .dateDebut(LocalDate.of(2023, 10, 1))
                    .dateFin(LocalDate.of(2023, 12, 31))
                    .statut(StatutAffectation.RETOURNEE)
                    .build();

            affectationRepository.save(aff1);
            affectationRepository.save(aff2);
            affectationRepository.save(aff3);
            affectationRepository.save(aff4);
        }
    }
}
