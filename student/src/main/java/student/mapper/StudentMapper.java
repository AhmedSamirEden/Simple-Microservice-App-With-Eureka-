package student.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import student.model.dto.StudentDto;
import student.model.entity.Student;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto studentToDto(Student student);

    Student dtoToStudent(StudentDto studentDto);

}
