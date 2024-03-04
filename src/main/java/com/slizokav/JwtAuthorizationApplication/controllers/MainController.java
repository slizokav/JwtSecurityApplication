package com.slizokav.JwtAuthorizationApplication.controllers;

import com.slizokav.JwtAuthorizationApplication.model.Person;
import com.slizokav.JwtAuthorizationApplication.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private PersonService personService;

    @Autowired
    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        personService.create(person);
        return "redirect:/main";
    }

}
