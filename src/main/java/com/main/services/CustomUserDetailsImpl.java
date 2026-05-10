package com.main.services;

import com.main.entities.User;
import com.main.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findUserByUsername(username);
       if(user!=null){
          UserDetails userDetails= org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
                   .password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
           return userDetails;
       }
        throw  new UsernameNotFoundException("user is not found with username: "+username);
    }
}
