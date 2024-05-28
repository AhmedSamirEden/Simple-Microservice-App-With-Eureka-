package school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.feign.FeignClientSchool;
import school.mapper.SchoolMapper;
import school.model.dto.SchoolDto;
import school.model.entity.School;
import school.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    private final FeignClientSchool feignClientSchool;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository, FeignClientSchool feignClientSchool) {
        this.schoolRepository = schoolRepository;
        this.feignClientSchool = feignClientSchool;
    }


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
    public Optional<SchoolDto> getSchool(Long id) {
        return schoolRepository.findById(id).map(school -> SchoolMapper.INSTANCE.SchoolToDto(school));
    }

    @Transactional
    public List<SchoolDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream().map(school -> SchoolMapper.INSTANCE.SchoolToDto(school))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    @Transactional
    public List<String> getAllNames() {
        return feignClientSchool.getAllNames();
    }

}
