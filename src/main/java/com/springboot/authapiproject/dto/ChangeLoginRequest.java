package com.springboot.authapiproject.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLoginRequest {
    private static final Logger logger = LogManager.getLogger(ChangeRoleRequest.class.getSimpleName());
    private String newLogin;

    public String getNewLogin() {
        logger.info("Method getNewLogin called");
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        logger.info("Method setNewLogin called");
        this.newLogin = newLogin;
    }
}
