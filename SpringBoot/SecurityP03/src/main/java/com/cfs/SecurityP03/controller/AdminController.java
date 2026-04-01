package com.cfs.SecurityP03.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "admin accessing dashboard";
    }


    //already protected by URL (/api/admin/**)
    //adding @PreAuthorize as extra layer
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")  //extra protection
    public List<String> getAllUserData()
    {
        List<String> users=new ArrayList<>();
        users.add(new String("Raj"));
        users.add(new String("priya"));
        users.add(new String("tina"));
        users.add(new String("shivam"));
        users.add(new String("hariya"));

        return users;
    }
}
