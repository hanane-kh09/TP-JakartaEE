package ma.projet.parcinformatique.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.projet.parcinformatique.enums.StatutAffectation;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffectationForm {

    private Long id;

    @NotNull(message = "Le matériel est obligatoire")
    private Long materielId;

    @NotNull(message = "L'employé est obligatoire")
    private Long employeId;

    @NotNull(message = "La date de début est obligatoire")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateDebut;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFin;

    @NotNull(message = "Le statut de l'affectation est obligatoire")
    private StatutAffectation statut;
}
