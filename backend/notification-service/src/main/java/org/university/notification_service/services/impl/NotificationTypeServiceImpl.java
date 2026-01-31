package org.university.notification_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.university.notification_service.dtos.NotificationTypeDto;
import org.university.notification_service.dtos.SingleMessageDto;
import org.university.notification_service.exceptions.NotificationConstraintException;
import org.university.notification_service.exceptions.NotificationTypeNotFoundException;
import org.university.notification_service.mappers.NotificationTypeMapper;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.repositories.NotificationTypeRepository;
import org.university.notification_service.services.NotificationTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    @Autowired
    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    public List<NotificationTypeDto> findAll() {

        List<NotificationTypeDto> notificationTypeDtos = new ArrayList<>();

        notificationTypeRepository.findAll().forEach(notificationType -> {
            notificationTypeDtos.add(notificationTypeMapper.toNotificationTypeDtoFromEntity(notificationType));
        });

        return notificationTypeDtos;
    }

    @Override
    public NotificationTypeDto createNotificationType(NotificationTypeDto notificationTypeDto) {

        NotificationType notificationType = new NotificationType();
        notificationTypeMapper.updateEntityFromDto(notificationTypeDto, notificationType);

        try {

            return notificationTypeMapper.toNotificationTypeDtoFromEntity(notificationTypeRepository.save(notificationType));

        } catch (RuntimeException e) {
            throw new NotificationConstraintException("The name of the notification type must be unique!");
        }

    }

    public NotificationTypeDto findById(Long id) {

        Optional<NotificationType> notificationTypeOptional = notificationTypeRepository.findById(id);

        if(!notificationTypeOptional.isPresent()) {
            throw new NotificationTypeNotFoundException("Notification type with the given id doesn't exist!");
        }

        return notificationTypeMapper.toNotificationTypeDtoFromEntity(notificationTypeOptional.get());

    }

    @Override
    public NotificationType findByTypeName(String typeName) {

        Optional<NotificationType> notificationTypeOptional = notificationTypeRepository.findByTypeName(typeName);

        if(!notificationTypeOptional.isPresent()) {
            throw new RuntimeException("This notification type doesn't exist!");
        }

        return notificationTypeOptional.get();
    }

    @Override
    public NotificationTypeDto updateNotificationType(Long id, NotificationTypeDto notificationTypeDto) {

        Optional<NotificationType> notificationTypeOptional = notificationTypeRepository.findById(id);

        if(!notificationTypeOptional.isPresent()) {
            throw new NotificationTypeNotFoundException("Notification type with the given id doesn't exist!");
        }

        NotificationType notificationType = notificationTypeOptional.get();
        notificationTypeMapper.updateEntityFromDto(notificationTypeDto, notificationType);

        try {

            return notificationTypeMapper.toNotificationTypeDtoFromEntity(notificationTypeRepository.save(notificationType));

        } catch (RuntimeException e) {
            throw new NotificationConstraintException("The name of the notification type must be unique!");
        }


    }

    public void deleteById(Long id) {

        try {
            notificationTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotificationTypeNotFoundException("Notification type with the given id doesn't exist!");
        }

    }


}
