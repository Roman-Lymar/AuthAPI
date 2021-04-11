package com.springboot.authapiproject.dto;



import com.springboot.authapiproject.exceptions.ErrorMessages;
import com.springboot.authapiproject.exceptions.ResourceNotFoundException;
import com.springboot.authapiproject.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;


public class RegistrationRequest {

    private static final Logger logger = LogManager.getLogger(RegistrationRequest.class.getSimpleName());

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public String getLogin() {
        logger.info("Method getLogin called");
        return login;
    }

    public void setLogin(String login) {
        logger.info("Method setLogin called");
        this.login = login;
    }

    public String getPassword() {
        logger.info("Method getPassword called");
        return password;
    }
    public void setPassword(String password) {
        logger.info("Method setPassword called");
        this.password = password;}


}


