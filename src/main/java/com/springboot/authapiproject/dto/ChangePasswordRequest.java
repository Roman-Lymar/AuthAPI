package com.springboot.authapiproject.dto;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ChangePasswordRequest {

    private static final Logger logger = LogManager.getLogger(ChangePasswordRequest.class.getSimpleName());


    private String password;
    private String newPassword;

    public String getPassword() {
        logger.info("Method getPassword called");
        return password;
    }

    public void setPassword(String password) {
        logger.info("Method setPassword called");
        this.password = password;}

    public String getNewPassword() {
        logger.info("Method getNewPassword called");
        return newPassword;}

    public void setNewPassword(String newPassword) {
        logger.info("Method setNewPassword called");
        this.newPassword = newPassword;}


    }



