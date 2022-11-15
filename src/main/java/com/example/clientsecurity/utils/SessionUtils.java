package com.example.clientsecurity.utils;

import com.example.clientsecurity.model.AuthToken;
import com.example.clientsecurity.model.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtils {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_DEPUTY = "DEPUTY";
    public static final String ROLE_LEAD = "LEAD";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_OPERATOR = "OPERATOR";
    public static final String OPERATOR_ROLE = "Operator";


    public static final String USER_TYPE = "USER_TYPE";

    public static UserSession setSession(HttpServletRequest request, AuthToken token){
        UserSession oldSession = SessionUtils.getSession(request);
        if(oldSession == null){
            HttpSession httpSession = request.getSession(true);

            UserSession session = new UserSession();
            session.setSaveRequestCount(0);
            session.setCsrfToken(null);

            if(token != null){
                if(token.getEmployeeId() != null){
                    session.setEmployeeId(token.getEmployeeId().longValue());
                }
                session.setUserId(token.getUserId());
                if(token.getTeamId() != null){
                    session.setTeamId(token.getTeamId().longValue());
                }
                if(token.getLeadId() != null){
                    session.setLeadId(token.getLeadId().longValue());
                }

                session.setUserType((getUserType(token.getAuthorities())));
                session.setAuthorities(token.getAuthorities());
            }

            session.setJsessionId(System.currentTimeMillis() + "client-security");

            CSRFTokenManager.setTokenInSession(session);
            httpSession.setAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME, session);

            return session;
        }else{
            HttpSession httpSession = request.getSession(false);

            oldSession.setSaveRequestCount(0);
            oldSession.setCsrfToken(null);

            if(token != null){
                if(token.getEmployeeId() != null){
                    oldSession.setEmployeeId(token.getEmployeeId().longValue());
                }
                oldSession.setUserId(token.getUserId());
                if(token.getTeamId() != null){
                    oldSession.setTeamId(token.getTeamId().longValue());
                }
                if(token.getLeadId() != null){
                    oldSession.setLeadId(token.getLeadId().longValue());
                }

                oldSession.setUserType((getUserType(token.getAuthorities())));
                oldSession.setAuthorities(token.getAuthorities());
            }

            oldSession.setJsessionId(System.currentTimeMillis() + "client-security");

            CSRFTokenManager.setTokenInSession(oldSession);
            httpSession.setAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME, oldSession);

            return oldSession;
        }
    }

    public static String getUserType(List<String> authorities) {
        String userType = ROLE_EMPLOYEE;
        boolean isAdmin = false;
        boolean isManager = false;
        boolean isLead = false;
        boolean isOperator = false;
        boolean isDeputy = false;

        if(authorities != null && authorities.size() > 0){
            for (String authority : authorities){
                if(!authority.isEmpty() && authority.trim().length() > 0){
                    if(authority.equalsIgnoreCase(ROLE_ADMIN)){
                        isAdmin = true;
                    }
                    if(authority.equalsIgnoreCase(ROLE_MANAGER)){
                        isManager = true;
                    }
                    if(authority.equalsIgnoreCase(ROLE_OPERATOR)){
                        isOperator = true;
                    }
                    if(authority.equalsIgnoreCase(ROLE_LEAD)){
                        isLead = true;
                    }
                    if(authority.equalsIgnoreCase(ROLE_DEPUTY)){
                        isDeputy = true;
                    }
                }
            }
        }

        if(isAdmin){
            userType = ROLE_ADMIN;
        }else if(isManager){
            userType = ROLE_MANAGER;
        }else if(isOperator){
            userType = ROLE_OPERATOR;
        }else if(isDeputy){
            userType = ROLE_DEPUTY;
        }else if(isLead){
            userType = ROLE_LEAD;
        }

        return userType;
    }

    public static UserSession getSession(HttpServletRequest request) {
        try{
            Object  object = new Object();
            HttpSession httpSession;
            httpSession = request.getSession(false);
            object = httpSession.getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME);

            return (UserSession) object;
        }catch(Exception e){
            return null;
        }
    }
}
