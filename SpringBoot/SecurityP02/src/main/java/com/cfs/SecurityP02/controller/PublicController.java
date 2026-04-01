package com.cfs.SecurityP02.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/hello")
    public String hello()
    {
        return "hello sir";
    }


    @GetMapping("/health")
    public String health()
    {
        return "application is up and healthy!";
    }


    @GetMapping("/info")
    public String info()
    {
        return "get loan @6%";
    }
}
