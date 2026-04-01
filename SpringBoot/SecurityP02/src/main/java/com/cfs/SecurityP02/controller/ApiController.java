package com.cfs.SecurityP02.controller;


import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/user")
    public Map<String,Object> userEndPoint()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> response=new HashMap<>();
        response.put("message","Welcome to user Endpoint");
        response.put("endpoint","/api/user");
        response.put("accessLevel","Authenticated user");
        response.put("timestamp", LocalDate.now().toString());
        response.put("user",getUserInfo(auth));
        return  response;
    }


    @GetMapping("/admin")
    public Map<String,Object> adminEndPoint()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> response=new HashMap<>();
        response.put("message","Welcome to Admin Endpoint");
        response.put("endpoint","/api/user");
        response.put("accessLevel","Authenticated user");
        response.put("timestamp", LocalDate.now().toString());
        response.put("user",getUserInfo(auth));
        response.put("adminSecret","You have admin access");
        return  response;
    }


    private Map<String,Object> getUserInfo(Authentication authentication)
    {
        Map<String,Object> userInfo=new HashMap<>();
        userInfo.put("username",authentication.getName());
        userInfo.put("roles",authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        userInfo.put("authenticated",authentication.isAuthenticated());
        return userInfo;
    }
}
