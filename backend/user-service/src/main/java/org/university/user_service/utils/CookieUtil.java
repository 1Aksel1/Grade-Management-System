package org.university.user_service.utils;

import org.springframework.stereotype.Component;
import org.university.user_service.exceptions.BadRequestException;

@Component
public class CookieUtil {

    public String getSessionIdFromCookie(String cookie) {

        try {

            String[] cookieSplit = cookie.split(";");
            String[] sessionIdSplit = cookieSplit[0].split("=");
            String sessionId = sessionIdSplit[1];

            return sessionId;

        } catch (Exception e) {
            throw new BadRequestException();
        }

    }



}
