package org.university.user_service.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.university.user_service.dtos.UpdateAdminDto;
import org.university.user_service.model.Admin;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdminMapper {

    UpdateAdminDto toUpdateDtoFromEntity(Admin admin);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateAdminFromDto(UpdateAdminDto updateAdminDto, @MappingTarget Admin admin);


}
