package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Cours;
import ma.fst.studentapi.entity.Student;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.StudentMapper;
import ma.fst.studentapi.repository.CoursRepository;
import ma.fst.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CoursRepository coursRepository;


    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper,
                          CoursRepository coursRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.coursRepository = coursRepository;
    }

    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student = studentMapper.toEntity(dto);
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Étudiant introuvable avec l'id : " + id));
        return studentMapper.toResponseDTO(student);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Étudiant introuvable avec l'id : " + id));
        studentMapper.updateEntityFromDTO(dto, student);
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Étudiant introuvable avec l'id : " + id));
        studentRepository.delete(student);
    }

    public StudentResponseDTO assignCours(Long studentId, Long coursId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Étudiant introuvable avec l'id : " + studentId));
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cours introuvable avec l'id : " + coursId));
        student.setCours(cours);
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }
}