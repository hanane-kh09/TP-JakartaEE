package ma.projet.parcinformatique.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.TypeMateriel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materiels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La référence est obligatoire")
    @Column(unique = true, nullable = false)
    private String ref;

    @NotNull(message = "Le type de matériel est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMateriel type;

    @NotBlank(message = "La marque est obligatoire")
    @Column(nullable = false)
    private String marque;

    @NotNull(message = "L'état du matériel est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatMateriel etat;

    @Column(name = "date_achat")
    private LocalDate dateAchat;

    @JsonIgnore
    @OneToMany(mappedBy = "materiel", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Affectation> affectations = new ArrayList<>();
}
