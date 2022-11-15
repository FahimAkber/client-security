package com.example.clientsecurity.controller;

import com.example.clientsecurity.model.AuthToken;
import com.example.clientsecurity.model.UserSession;
import com.example.clientsecurity.service.AuthService;
import com.example.clientsecurity.utils.CSRFTokenManager;
import com.example.clientsecurity.utils.SessionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

@Controller
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ModelAndView getLogInPage(HttpServletRequest request){
        return new ModelAndView("logIn");
    }

    @GetMapping("/welcome")
    public String welcomeUser(HttpServletRequest request){
        return "welcome";
    }

    @GetMapping("/right-error")
    public ModelAndView showResponse(HttpServletRequest request){
        return new ModelAndView("error");
    }

    @PostMapping("/verification")
    @ResponseBody
    public ResponseEntity<String> verifyUser(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("password");
//        ModelAndView mav = new ModelAndView(new RedirectView("/login"));;
//        if(userName != null && !userName.isEmpty() && userPassword != null && !userPassword.isEmpty()){
//            ResponseEntity<AuthToken> response = authService.getAuthentication(userName, userPassword);
//            if(response == null){
//                redirectAttributes.addFlashAttribute("message", "User Not Found");
//            }else{
//                mav = new ModelAndView(new RedirectView("/welcome"));
//            }
//        }else {
//            redirectAttributes.addFlashAttribute("message", "Please Select mandatory fields.");
//        }

        String message = "";
        if(userName != null && !userName.isEmpty() && userPassword != null && !userPassword.isEmpty()){
            ResponseEntity<AuthToken> response = authService.getAuthentication(userName, userPassword);

            if(response == null){
                message = "user not found";
            }else{
                message = "success";
                UserSession userSession = SessionUtils.setSession(request, response.getBody());
            }
        }else {
            message = "Please select mandatory fields.";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

    }

    @PostMapping("/logout")
    public RedirectView getLogOut(HttpServletRequest request){
        request.setAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME, null);
        request.getSession().invalidate();

        return new RedirectView("/login");
    }



}
