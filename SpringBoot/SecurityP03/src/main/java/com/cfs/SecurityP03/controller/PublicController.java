package com.cfs.SecurityP03.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {


    @GetMapping("/hello")
    public String hello()
    {
        return "hello from CFS";
    }


    @GetMapping("/info")
    public String info()
    {
        return "New Course start from Apr";
    }
}
