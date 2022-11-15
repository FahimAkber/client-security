package com.example.clientsecurity.utils;

import com.example.clientsecurity.model.UserSession;

import java.util.UUID;

public class CSRFTokenManager {
    public static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = "client-security";

    public static UserSession setTokenInSession(UserSession session) {
        if(session.getCsrfToken() == null || session.getCsrfToken().isEmpty()){
            session.setCsrfToken(UUID.randomUUID().toString());
        }

        return session;
    }
}
