package student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
