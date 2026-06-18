package ma.fst.studentapi.dto;

import java.time.LocalDate;

public record CoursRequestDTO(
        String titre,
        String description,
        LocalDate dateDebut,
        LocalDate dateFin
) {}