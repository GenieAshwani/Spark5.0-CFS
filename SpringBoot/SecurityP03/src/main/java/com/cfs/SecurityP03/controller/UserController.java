package com.cfs.SecurityP03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/course")
    public String course()
    {
        return "you enrolled in Java full stack";
    }

    @GetMapping("/profile")
    public String profile()
    {
        return "you are student";
    }
}
