package com.springboot.authapiproject.dto;

import com.springboot.authapiproject.exceptions.ErrorMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;

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
