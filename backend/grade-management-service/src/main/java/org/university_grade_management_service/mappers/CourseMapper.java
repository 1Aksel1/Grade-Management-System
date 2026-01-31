package org.university_grade_management_service.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.university_grade_management_service.dtos.CourseGeneralInfoDto;
import org.university_grade_management_service.model.Course;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CourseMapper {

    CourseGeneralInfoDto toCourseGeneralInfoDtoFromEntity(Course course);





}
