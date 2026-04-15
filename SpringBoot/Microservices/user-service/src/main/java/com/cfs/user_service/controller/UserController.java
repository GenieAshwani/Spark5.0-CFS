package com.cfs.user_service.controller;


import com.cfs.user_service.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${server.port}")
    String port;

    private List<User> users= Arrays.asList(
            new User(1L,"ashwani","ashwani@gmail.com"),
            new User(2L,"rohit","rohit@gmail.com"),
            new User(3L,"aman","aman@gmail.com")
    );

    @GetMapping
    public List<User> getAllUsers()
    {
        System.out.println("running on port.....%s%n"+ port);
        return users;
    }


    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id)
    {
        System.out.println("calling getUserByID....");
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
