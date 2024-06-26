package student.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import student.mapper.StudentMapper;
import student.model.dto.StudentDto;
import student.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> addStudnet(@Valid @RequestBody StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(studentService.addStudent(studentDto));
    }

    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Optional<StudentDto> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Optional<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Long id) {
        return studentService.updateStudent(studentDto, id);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/students/names")
    public List<String> getAllNames() {
        return studentService.getAllNames();
    }
}
