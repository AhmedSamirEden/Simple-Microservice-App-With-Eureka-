package school.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import school.model.dto.SchoolDto;
import school.model.entity.School;

@Mapper
public interface SchoolMapper {
    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolDto SchoolToDto(School school);

    School dtoToSchool(SchoolDto schoolDto);
}
