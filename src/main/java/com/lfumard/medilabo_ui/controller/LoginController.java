package com.lfumard.medilabo_ui.controller;

import com.lfumard.medilabo_ui.beans.SignupRequestBean;
import com.lfumard.medilabo_ui.proxies.PatientProxies;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private String username;
    private String password;
    private ResponseEntity<?> responseEntity;

    @Autowired
    PatientProxies patientProxies;

    @GetMapping("/login")
    public String afficherPageLogin(Model model) {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {

        String strCookie = patientProxies.signup(new SignupRequestBean(username, password));
        Cookie cookie = new Cookie("medilabo", strCookie);
        response.addCookie(cookie);

        return "redirect:/patient/list";
    }
}
