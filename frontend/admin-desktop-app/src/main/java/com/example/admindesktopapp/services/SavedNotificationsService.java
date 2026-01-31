package com.example.admindesktopapp.services;

import com.example.admindesktopapp.dto.NotificationTypeDto;
import com.example.admindesktopapp.dto.SavedNotification;

import java.util.List;

public interface SavedNotificationsService {

    public List<SavedNotification> searchSavedNotifications(boolean activationEmail, boolean passwordChange, boolean examPeriodActivated, boolean gradeAdded, String email, String fromDate, String toDate);

}
