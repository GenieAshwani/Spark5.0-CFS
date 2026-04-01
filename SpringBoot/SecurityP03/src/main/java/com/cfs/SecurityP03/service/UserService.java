package com.cfs.SecurityP03.service;

import com.cfs.SecurityP03.dto.RegisterRequest;
import com.cfs.SecurityP03.entity.Role;
import com.cfs.SecurityP03.entity.User;
import com.cfs.SecurityP03.repository.RoleRepository;
import com.cfs.SecurityP03.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterRequest request)
    {
        if(userRepository.existsByUsername(request.getUsername()))
        {
            throw new RuntimeException("username already exists: "+request.getUsername());
        }

        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("email already exists: "+request.getEmail());
        }

        Role userRole=roleRepository.findByName("USER")
                .orElseGet(()->{
                    return roleRepository.save(Role.builder()
                            .name("USER")
                                    .description("Standard user role")
                                    .build()
                            )   ;
                });
        User user=User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        user.addRole(userRole);

        User savedUser=null;
        try{
             savedUser = userRepository.save(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return savedUser;
    }

    @Transactional
    public User registerAdmin(RegisterRequest request)
    {
        if(userRepository.existsByUsername(request.getUsername()))
        {
            throw new RuntimeException("username already exists: "+request.getUsername());
        }

        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("email already exists: "+request.getEmail());
        }

        Role userRole=roleRepository.findByName("USER")
                .orElseGet(()->{
                    return roleRepository.save(Role.builder()
                            .name("USER")
                            .description("Standard user role")
                            .build()
                    )   ;
                });

        Role adminRole=roleRepository.findByName("ADMIN")
                .orElseGet(()->{
                    return roleRepository.save(Role.builder()
                            .name("ADMIN")
                            .description("Admin role")
                            .build()
                    )   ;
                });


        User admin=User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        admin.addRole(userRole);
        admin.addRole(adminRole);

        User savedAdmin = userRepository.save(admin);
        return savedAdmin;
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }
}
