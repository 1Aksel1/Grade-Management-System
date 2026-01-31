package org.university.user_service.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.university.user_service.dtos.CreateStudentDto;
import org.university.user_service.dtos.UpdateStudentDto;
import org.university.user_service.model.Student;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper {

    Student toEntityFromCreateDto(CreateStudentDto createStudentDto);

    UpdateStudentDto toUpdateDtoFromEntity(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "registeredExams", ignore = true)
    void updateStudentFromDto(UpdateStudentDto updateStudentDto, @MappingTarget Student student);


}
