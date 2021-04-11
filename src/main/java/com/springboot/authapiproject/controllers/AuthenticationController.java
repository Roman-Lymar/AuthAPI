package com.springboot.authapiproject.controllers;


import com.springboot.authapiproject.config.jwt.JwtProvider;
import com.springboot.authapiproject.dto.AuthRequest;
import com.springboot.authapiproject.dto.AuthResponse;
import com.springboot.authapiproject.dto.RegistrationRequest;
import com.springboot.authapiproject.models.User;
import com.springboot.authapiproject.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class.getSimpleName());

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public AuthResponse registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) throws Exception {
        logger.info("Request signup");
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.registerUser(u);
        String token = jwtProvider.generateToken(u.getId().toString(), u.getLogin(), u.getRole().getName());
        logger.info("Control token created" );
        return new AuthResponse(token);
    }

    @PostMapping("/signin")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        logger.info("Request signin");
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getId().toString(), userEntity.getLogin(), userEntity.getRole().getName());
        return new AuthResponse(token);
    }
}
