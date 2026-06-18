package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.CoursRequestDTO;
import ma.fst.studentapi.dto.CoursResponseDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Cours;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.CoursMapper;
import ma.fst.studentapi.mapper.StudentMapper;
import ma.fst.studentapi.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CoursService {

    private final CoursRepository coursRepository;
    private final CoursMapper coursMapper;
    private final StudentMapper studentMapper;

    public CoursService(CoursRepository coursRepository,
                        CoursMapper coursMapper,
                        StudentMapper studentMapper) {
        this.coursRepository = coursRepository;
        this.coursMapper = coursMapper;
        this.studentMapper = studentMapper;
    }

    public CoursResponseDTO create(CoursRequestDTO dto) {
        Cours cours = coursMapper.toEntity(dto);
        return coursMapper.toResponseDTO(coursRepository.save(cours));
    }

    public List<CoursResponseDTO> getAll() {
        return coursRepository.findAll()
                .stream()
                .map(coursMapper::toResponseDTO)
                .toList();
    }

    public CoursResponseDTO getById(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with id: " + id));
        return coursMapper.toResponseDTO(cours);
    }

    public CoursResponseDTO update(Long id, CoursRequestDTO dto) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with id: " + id));
        coursMapper.updateEntityFromDTO(dto, cours);
        return coursMapper.toResponseDTO(coursRepository.save(cours));
    }

    public void delete(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours not found with id: " + id);
        }
        coursRepository.deleteById(id);
    }

    public List<StudentResponseDTO> getStudentsByCours(Long coursId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours not found with id: " + coursId));
        return cours.getStudents()
                .stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    public List<CoursResponseDTO> getCoursBetweenDates(LocalDate debut, LocalDate fin) {
        return coursRepository
                .findByDateDebutGreaterThanEqualAndDateFinLessThanEqual(debut, fin)
                .stream()
                .map(coursMapper::toResponseDTO)
                .toList();
    }
}