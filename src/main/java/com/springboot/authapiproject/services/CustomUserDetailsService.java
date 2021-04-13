package com.springboot.authapiproject.services;

import com.springboot.authapiproject.dto.CustomUserDetails;
import com.springboot.authapiproject.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getSimpleName());


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Method loadUserByUsername called");
        User userEntity = userService.findUserByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

    public CustomUserDetails loadUserById(String id) throws UsernameNotFoundException {
        logger.info("Method loadUserById called");
        User userEntity = userService.getUserById(UUID.fromString(id)).get();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

}
