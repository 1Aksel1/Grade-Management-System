package org.university.user_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.university.user_service.dtos.CreateProfessorDto;
import org.university.user_service.dtos.UpdateProfessorDto;
import org.university.user_service.model.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    Professor toEntityFromCreateDto(CreateProfessorDto createProfessorDto);

    UpdateProfessorDto toUpdateDtoFromEntity(Professor professor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateProfessorFromDto(UpdateProfessorDto updateProfessorDto, @MappingTarget Professor professor);


}
