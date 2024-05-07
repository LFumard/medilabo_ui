package com.lfumard.medilabo_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private String username;
    private String password;
    @GetMapping("/login")
    public String afficherPageLogin(Model model) {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        this.username = username;
        this.password = password;
        return "redirect:/patient/list";
    }
}
