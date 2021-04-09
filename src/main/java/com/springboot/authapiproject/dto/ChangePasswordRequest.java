package com.springboot.authapiproject.dto;


import com.springboot.authapiproject.exceptions.BadPassworExeption;
import com.springboot.authapiproject.exceptions.ErrorMessages;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

public class ChangePasswordRequest {

    private String password;
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

            this.password = password;

                    }

    public String getNewPassword() {

        return newPassword;

    }

    public void setNewPassword(String newPassword) throws Exception {
        if(isValid(newPassword)){
            throw new BadPassworExeption(ErrorMessages.BAD_PASSWORD.getErrorMessage());
        }  else
        this.newPassword = newPassword;
        }

    }



