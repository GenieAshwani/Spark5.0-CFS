package com.cfs.SecurityP03.service;

import com.cfs.SecurityP03.entity.User;
import com.cfs.SecurityP03.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("custom loadbyusername running...........");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("getting use ---->"+ user);
        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(User user) {
       Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role->{
                    String roleName =role.getName().startsWith("ROLE_") ? role.getName() : "ROLE_"+role.getName();
                    return new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toList());

       return org.springframework.security.core.userdetails.User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .authorities(authorities)
               .accountExpired(false)
               .accountLocked(!user.isAccountNonLocked())
               .credentialsExpired(false)
               .disabled(!user.isEnabled())
               .build();
    }
}
