package org.university.notification_service.services;

import org.springframework.stereotype.Service;
import org.university.notification_service.dtos.NotificationTypeDto;
import org.university.notification_service.dtos.SingleMessageDto;
import org.university.notification_service.model.NotificationType;

import java.util.List;

public interface NotificationTypeService {


    public List<NotificationTypeDto>  findAll();

    public NotificationTypeDto findById(Long id);

    public NotificationTypeDto createNotificationType(NotificationTypeDto notificationTypeDto);

    public NotificationType findByTypeName(String typeName);

    public NotificationTypeDto updateNotificationType(Long id, NotificationTypeDto notificationTypeDto);

    public void deleteById(Long id);



}
