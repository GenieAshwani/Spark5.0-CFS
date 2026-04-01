package com.cfs.SecurityP04.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/test")
    public String test()
    {
        return "Hi Buddy";
    }

    @GetMapping("/info")
    public String info()
    {
        return "last login at 9PM";
    }
}
