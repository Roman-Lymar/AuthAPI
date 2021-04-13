package com.springboot.authapiproject.dto;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ChangeRoleRequest {

    private static final Logger logger = LogManager.getLogger(ChangeRoleRequest.class.getSimpleName());

    private String newRole;

    public String getNewRole() {
        logger.info("Method getNewRole called");
        return newRole;
    }

    public void setNewRole(String newRole) {
        logger.info("Method setNewRole called");
        this.newRole = newRole;
    }

}
