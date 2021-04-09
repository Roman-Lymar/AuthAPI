package com.springboot.authapiproject.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 Secure Password requirements

 1.  Password must contain at least one digit [0-9].
 2.  Password must contain at least one lowercase Latin character [a-z].
 3. Password must contain at least one uppercase Latin character [A-Z].
 4. Password must contain at least one special character like ! @ # & ( ).
 5. Password must contain a length of at least 8 characters and a maximum of 20 characters.

 **/


public class PasswordValidator {
    private  final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private  final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(final String password){
        Matcher matcher = pattern.matcher(password);
       return matcher.matches();




        }
    }


