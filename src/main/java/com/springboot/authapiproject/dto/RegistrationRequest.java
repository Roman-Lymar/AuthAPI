package com.springboot.authapiproject.dto;


import com.springboot.authapiproject.exceptions.BadPassworExeption;
import com.springboot.authapiproject.exceptions.ErrorMessages;
import com.springboot.authapiproject.exceptions.ResourceNotFoundException;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotEmpty;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;


public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if(isValid(password)){

            throw new BadPassworExeption(ErrorMessages.BAD_PASSWORD.getErrorMessage());
        }  else
            this.password = password;
    }


        }


