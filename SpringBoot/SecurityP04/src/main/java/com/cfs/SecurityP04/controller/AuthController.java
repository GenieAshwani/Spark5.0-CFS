package com.cfs.SecurityP04.controller;


import com.cfs.SecurityP04.dto.LoginRequest;
import com.cfs.SecurityP04.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> createToken(@RequestBody LoginRequest request)
    {

        try {


        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(user.getUsername());

        Map<String,Object> response=new HashMap<>();
        response.put("token",token);
        response.put("type","Bearer");
        response.put("username",user.getUsername());
        response.put("roles",user.getAuthorities()
                .stream().map(a->a.getAuthority()).toList());
        response.put("expireTime",jwtService.getJwtExpirationTime());

        System.out.println("User logged in : "+user.getUsername());
        return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            Map<String,Object> response=new HashMap<>();
            response.put("error","invalid credentials");
            response.put("message",e.getMessage());
            return ResponseEntity.status(401).body(response);
        }
    }
}
