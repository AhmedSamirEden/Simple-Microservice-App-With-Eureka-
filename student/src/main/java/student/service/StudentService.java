package student.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import student.mapper.StudentMapper;
import student.model.dto.StudentDto;
import student.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public StudentDto addStudent(StudentDto sudentDto) {
        return StudentMapper.INSTANCE.studentToDto(studentRepository.save(StudentMapper.INSTANCE.dtoToStudent(sudentDto)));
    }

    @Transactional
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(student -> StudentMapper.INSTANCE.studentToDto(student)).collect(Collectors.toList());
    }

    @Transactional
    public Optional<StudentDto> getStudentById(Long id) {
        return studentRepository.findById(id).map(student -> StudentMapper.INSTANCE.studentToDto(student));
    }

    @Transactional
    public Optional<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, Long id) {
        Optional<StudentDto> optionalStudentDto = studentRepository.findById(id).map(student -> StudentMapper.INSTANCE.studentToDto(student));
        if (optionalStudentDto.isPresent()) {
            optionalStudentDto.get().setName(studentDto.getName());
            optionalStudentDto.get().setSchoolId(studentDto.getSchoolId());
            studentRepository.save(StudentMapper.INSTANCE.dtoToStudent(optionalStudentDto.get()));
        }
        return optionalStudentDto;
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
