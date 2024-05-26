package school.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.mapper.SchoolMapper;
import school.model.dto.SchoolDto;
import school.model.entity.School;
import school.repository.SchoolRepository;

import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Transactional
    public SchoolDto addSchool(SchoolDto schoolDto) {
        School school = schoolRepository.save(SchoolMapper.INSTANCE.dtoToSchool(schoolDto));
        return SchoolMapper.INSTANCE.SchoolToDto(school);
    }

    @Transactional
    public SchoolDto updateSchool(Long id, SchoolDto schoolDto) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setName(schoolDto.getName());
        }
        return SchoolMapper.INSTANCE.SchoolToDto(optionalSchool.get());
    }

    @Transactional
    public SchoolDto getSchool(Long id) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        return SchoolMapper.INSTANCE.SchoolToDto(optionalSchool.get());
    }

    @Transactional
    public void removeSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
