package com.main.services;

import com.main.entities.User;
import com.main.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user != null){

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            System.out.println("Password Match Result: " +
                    encoder.matches("Sita", user.getPassword()));

            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
