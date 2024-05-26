package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.model.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
