package ma.projet.parcinformatique.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.projet.parcinformatique.enums.StatutAffectation;

import java.time.LocalDate;

@Entity
@Table(name = "affectations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date de début est obligatoire")
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @NotNull(message = "Le statut de l'affectation est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutAffectation statut;

    @NotNull(message = "Le matériel est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    @NotNull(message = "L'employé est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;
}
