package org.university.notification_service.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.university.notification_service.model.SavedNotification;
import org.university.notification_service.repositories.SavedNotificationRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("savedNotificationRepository")
public class SavedNotificationRepositoryImpl extends SimpleJpaRepository<SavedNotification, Long> implements SavedNotificationRepository {

    private EntityManager entityManager;

    @Autowired
    public SavedNotificationRepositoryImpl(EntityManager em) {
        super(SavedNotification.class, em);
        this.entityManager = em;
    }

    public List<SavedNotification> findAllForAdmin(String email, List<Long> typeIds, LocalDateTime fromDate, LocalDateTime toDate) {
        return findAllNotifications(email, typeIds, fromDate, toDate, false);
    }


    public List<SavedNotification> findAllForStudentOrProfessor(String email, List<Long> typeIds, LocalDateTime fromDate, LocalDateTime toDate) {
        return findAllNotifications(email, typeIds, fromDate, toDate, true);
    }

    private List<SavedNotification> findAllNotifications(String email, List<Long> typeIds, LocalDateTime fromDate, LocalDateTime toDate, boolean strictEmailMatch) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SavedNotification> criteriaQuery = criteriaBuilder.createQuery(SavedNotification.class);
        Root<SavedNotification> root = criteriaQuery.from(SavedNotification.class);

        List<Predicate> predicates = new ArrayList<>();

        if(strictEmailMatch) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        } else if (email != null && !email.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
        }


        if(typeIds.isEmpty()) {
            return new ArrayList<>();
        }

        predicates.add(root.get("notificationType").in(typeIds));

        if(fromDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sentTimeAndDate"), fromDate));
        }

        if(toDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sentTimeAndDate"), toDate));
        }


        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();


    }



}
