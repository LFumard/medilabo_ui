package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.SignupRequestBean;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils.*;
@Controller
public class LoginController {

    private String username;
    private String password;
    private ResponseEntity<?> responseEntity;

    @Autowired
    PatientProxies patientProxies;

    //private ResponseEntity<?> signupRequestBean;
    //private HttpServletRequest httpServletRequest;
    @GetMapping("/login")
    public String afficherPageLogin(Model model) {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {

        this.username = username;
        this.password = password;
        //this.responseEntity = patientProxies.signup(new SignupRequestBean(username, password));

        //httpServletRequest = patientProxies.signup(new SignupRequestBean(username, password));
        String strCookie = patientProxies.signup(new SignupRequestBean(username, password));


        /*String authHeader = signupRequestBean.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);
        if (authHeader != null && authHeader.startsWith("medilabo=")) {
            authHeader = authHeader.substring(9);
        }*/

        //signupRequestBean = this.responseEntity;

        Cookie cookie = new Cookie("medilabo", strCookie);
        response.addCookie(cookie);

        return "redirect:/patient/list";
    }
}
