package ma.fst.studentapi.controller;

import ma.fst.studentapi.dto.CoursRequestDTO;
import ma.fst.studentapi.dto.CoursResponseDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.service.CoursService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @PostMapping
    public ResponseEntity<CoursResponseDTO> create(@RequestBody CoursRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coursService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CoursResponseDTO>> getAll() {
        return ResponseEntity.ok(coursService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody CoursRequestDTO dto) {
        return ResponseEntity.ok(coursService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coursService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudents(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.getStudentsByCours(id));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<CoursResponseDTO>> getDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(coursService.getCoursBetweenDates(debut, fin));
    }
}