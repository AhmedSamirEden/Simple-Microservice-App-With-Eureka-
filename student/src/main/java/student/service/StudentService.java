package student.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.mapper.StudentMapper;
import student.model.dto.StudentDto;
import student.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public StudentDto addStudent(StudentDto sudentDto) {
        return StudentMapper.INSTANCE.studentToDto(studentRepository.save(StudentMapper.INSTANCE.dtoToStudent(sudentDto)));
    }

}
