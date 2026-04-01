package com.cfs.SecurityP04.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @GetMapping("/dashboard")
    public String adminDashboard()
    {
        return "welcome to admin dashboard";
    }


    @GetMapping("/users")
    public String getUsers()
    {
        return "Ashwani,Ajay,Raj,Rani,Anita";

    }
}
