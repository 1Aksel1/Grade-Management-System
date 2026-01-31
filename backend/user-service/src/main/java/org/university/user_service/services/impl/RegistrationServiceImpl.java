package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university.user_service.dtos.ActivateRegistrationNotiDto;
import org.university.user_service.exceptions.RegistrationObjectNotFoundException;
import org.university.user_service.helpers.MessageHelper;
import org.university.user_service.model.Registration;
import org.university.user_service.model.UserType;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.RegistrationRepository;
import org.university.user_service.repositories.StudentRepository;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.services.SendNotificationsService;
import org.university.user_service.utils.CookieUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private SendNotificationsService sendNotificationsService;
    private CookieUtil cookieUtil;


    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository, StudentRepository studentRepository, ProfessorRepository professorRepository, SendNotificationsService sendNotificationsService, CookieUtil cookieUtil) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.sendNotificationsService = sendNotificationsService;
        this.cookieUtil = cookieUtil;
    }

    public void createRegistration(Long userId, String name, String surname, UserType userType, String email) {

        UUID uuid = UUID.randomUUID();
        UUID sessionId = UUID.randomUUID();

        Registration registration = new Registration(uuid.toString(), userId, userType, email, LocalDateTime.now(), sessionId.toString());
        registrationRepository.save(registration);

        sendNotificationsService.sendRegisterActivationNotification(email, name, surname, userType, uuid.toString());

    }


    @Transactional
    public void resendRegistrationNotification(String cookie) {

        String sessionId = cookieUtil.getSessionIdFromCookie(cookie);

        Optional<Registration> registrationOptional = registrationRepository.findRegistrationBySessionId(sessionId);

        if(!registrationOptional.isPresent()) {
            throw new RegistrationObjectNotFoundException();
        }

        Registration registration = registrationOptional.get();
        registration.setCreationTime(LocalDateTime.now());
        registrationRepository.save(registration);

        String email = registration.getUserEmail();
        Long userId = registration.getUserId();
        UserType userType = registration.getUserType();
        String activationLinkIdentifier = registration.getLinkIdentifier();

        if(userType.equals(UserType.STUDENT)) {

            studentRepository.findById(userId).ifPresent(student -> {
                sendNotificationsService.sendRegisterActivationNotification(email, student.getName(), student.getSurname(), userType, activationLinkIdentifier);
            });

        } else if (userType.equals(UserType.PROFESSOR)) {

            professorRepository.findById(userId).ifPresent(professor -> {
                sendNotificationsService.sendRegisterActivationNotification(email, professor.getName(), professor.getSurname(), userType, activationLinkIdentifier);
            });

        }

    }

    @Transactional
    public void activateUser(String activationLink) {

        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByLinkIdentifier(activationLink);

        if(!registrationOptional.isPresent()) {
            throw new RegistrationObjectNotFoundException();
        }

        Registration registration = registrationOptional.get();
        registrationRepository.deleteById(registration.getId());


        Long userId = registration.getUserId();
        UserType userType = registration.getUserType();

        if(userType.equals(UserType.STUDENT)) {
            studentRepository.activateStudentRegistration(userId);
        } else if (userType.equals(UserType.PROFESSOR)){
            professorRepository.activateProfessorRegistration(userId);
        }


    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void removeExpiredRegistrations() {

        List<Registration> expiredRegistrations = registrationRepository.findExpiredRegistrations(LocalDateTime.now().minusMinutes(5));

        for(Registration expiredRegistration: expiredRegistrations) {

            registrationRepository.delete(expiredRegistration);

            Long userId = expiredRegistration.getUserId();
            UserType userType = expiredRegistration.getUserType();


            if(userType.equals(UserType.STUDENT)) {
                studentRepository.deleteById(userId);
            } else if (userType.equals(UserType.PROFESSOR)){
                professorRepository.deleteById(userId);
            }

        }

    }

    public String getSessionIdForUserIdAndUserType(Long userId, UserType userType) {

        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByUserIdAndUserType(userId, userType);

        if(!registrationOptional.isPresent()) {
            throw new RegistrationObjectNotFoundException();
        }

        return registrationOptional.get().getSessionId();


    }


}
