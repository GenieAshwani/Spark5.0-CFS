package com.cfs.SecurityP03.controller;


import com.cfs.SecurityP03.dto.RegisterRequest;
import com.cfs.SecurityP03.entity.User;
import com.cfs.SecurityP03.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> register(@Valid @RequestBody RegisterRequest request)
    {
        try{
            User user=userService.registerUser(request);
            Map<String,Object> response=new HashMap<>();

            response.put("success",true);
            response.put("message","user registered successfully");
            response.put("username",user.getUsername());
            response.put("email",user.getEmail());
            response.put("roles",user.getRoles().stream().map(r->r.getName()).toList());

            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            Map<String,Object> response=new HashMap<>();

            response.put("success",false);
            response.put("message","user not registered successfully");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<Map<String,Object>> registerAdmin(@Valid @RequestBody RegisterRequest request)
    {
        try{
            User user=userService.registerAdmin(request);
            Map<String,Object> response=new HashMap<>();

            response.put("success",true);
            response.put("message","Admin registered successfully");
            response.put("username",user.getUsername());
            response.put("email",user.getEmail());
            response.put("roles",user.getRoles().stream().map(r->r.getName()).toList());

            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            Map<String,Object> response=new HashMap<>();

            response.put("success",false);
            response.put("message","user not registered successfully");
            return ResponseEntity.ok(response);
        }
    }
}
