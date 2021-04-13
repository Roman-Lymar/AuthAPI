package com.springboot.authapiproject.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.NotEmpty;



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


