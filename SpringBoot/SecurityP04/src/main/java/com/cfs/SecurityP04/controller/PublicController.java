package com.cfs.SecurityP04.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {



    @GetMapping("/hello")
    public String hello()
    {
        return "Hello buddy";
    }

    @GetMapping("/info")
    public String info()
    {
        return "GenAI course starting from  6Apr";
    }
}
