package com.springboot.authapiproject.controllers;


import com.springboot.authapiproject.dto.ChangePasswordRequest;
import com.springboot.authapiproject.models.User;
import com.springboot.authapiproject.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class SecureController {

    private static final Logger logger = LogManager.getLogger(SecureController.class.getSimpleName());

    private static final String PATH_VARIABLE_ID = "id";

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PatchMapping(path="{id}/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updatePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest,
                                               @PathVariable(PATH_VARIABLE_ID) final UUID id,
                                               @RequestHeader("Authorization") String header) throws Exception {

        if(userServiceImpl.compareId(header, id)){
            User user = userServiceImpl.getUserById(id).get();

            if (changePasswordRequest.getPassword()!= null
                    && changePasswordRequest.getNewPassword()!=null) {
                userServiceImpl.changePassword(user, changePasswordRequest.getPassword(),
                        changePasswordRequest.getNewPassword());
            }

            return ResponseEntity.ok(user);
        }
        else{
            throw new Exception("dont have access");
        }

    }


}
