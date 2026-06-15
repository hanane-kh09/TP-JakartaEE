package com.example.demo.controllers;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        return new ResponseEntity<>(studentService.countStudents(), HttpStatus.OK);
    }

    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        return new ResponseEntity<>(studentService.findNbrStudentByYear(), HttpStatus.OK);
    }
}