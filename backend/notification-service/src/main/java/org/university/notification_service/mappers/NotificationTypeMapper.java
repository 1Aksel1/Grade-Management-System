package org.university.notification_service.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.university.notification_service.dtos.NotificationTypeDto;
import org.university.notification_service.model.NotificationType;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NotificationTypeMapper {

    NotificationTypeDto toNotificationTypeDtoFromEntity(NotificationType notificationType);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(NotificationTypeDto notificationTypeDto, @MappingTarget NotificationType notificationType);









}
