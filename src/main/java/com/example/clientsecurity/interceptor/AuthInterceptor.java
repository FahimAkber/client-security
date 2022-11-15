package com.example.clientsecurity.interceptor;

import com.example.clientsecurity.model.UserSession;
import com.example.clientsecurity.utils.CSRFTokenManager;
import com.example.clientsecurity.utils.SessionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if(!request.getMethod().equalsIgnoreCase("GET")){
            return true;
        }
        try{
            UserSession userSession = (UserSession) session.getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            String uri = request.getRequestURI();

            if(!uri.contains())
            if(!uri.contains("login")){
                if(userSession == null){
                    response.sendRedirect("/login");
                    return false;
                }else{
                    if(uri.contains("right-error") || uri.contains("logout")){
                        return true;
                    }else if(!userSession.getUserType().equalsIgnoreCase(SessionUtils.ROLE_EMPLOYEE)){
                        response.sendRedirect("/right-error");
                        return false;
                    }else{
                        return true;
                    }
                }
            } else{
                return true;
            }

        }catch (Exception e){

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
