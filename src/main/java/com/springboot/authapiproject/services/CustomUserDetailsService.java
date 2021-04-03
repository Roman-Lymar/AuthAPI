package com.springboot.authapiproject.services;

import com.springboot.authapiproject.dto.CustomUserDetails;
import com.springboot.authapiproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userService.findUserByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

    public CustomUserDetails loadUserById(String id) throws UsernameNotFoundException {
        User userEntity = userService.getUserById(UUID.fromString(id)).get();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

}
