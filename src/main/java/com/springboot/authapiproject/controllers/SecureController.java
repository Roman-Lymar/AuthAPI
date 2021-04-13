package com.springboot.authapiproject.controllers;

import com.springboot.authapiproject.dto.ChangeLoginRequest;
import com.springboot.authapiproject.dto.ChangePasswordRequest;
import com.springboot.authapiproject.dto.ChangeRoleRequest;
import com.springboot.authapiproject.models.User;
import com.springboot.authapiproject.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/")
public class SecureController {

    private static final Logger logger = LogManager.getLogger(SecureController.class.getSimpleName());

    private static final String PATH_VARIABLE_ID = "id";

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PatchMapping(path="{id}/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest,
                                            @PathVariable(PATH_VARIABLE_ID) final UUID id,
                                            @RequestHeader("Authorization") String header) throws Exception {
        logger.info("Request change-password");
        if(userServiceImpl.compareId(header, id)){
                User user = userServiceImpl.changePassword(id, changePasswordRequest.getPassword(),
                        changePasswordRequest.getNewPassword());
            return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
        }
        else{
            throw new Exception("Don't have access");
        }

    }

    @PatchMapping(path="{id}/change-login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeLogin(@RequestBody @Valid ChangeLoginRequest changeLoginRequest,
                                            @PathVariable(PATH_VARIABLE_ID) final UUID id,
                                            @RequestHeader("Authorization") String header) throws Exception {
        logger.info("Request change-login");
        if(userServiceImpl.compareId(header, id)){
            User user = userServiceImpl.changeLogin(id, changeLoginRequest.getNewLogin());

            return ResponseEntity.status(HttpStatus.OK).body("Login changed successfully");
        }
        else{
            throw new Exception("Don't have access");
        }

    }

    @PatchMapping(path="{id}/change-role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeRole(@RequestBody @Valid ChangeRoleRequest changeRoleRequest,
                                               @PathVariable(PATH_VARIABLE_ID) final UUID id){
        logger.info("Request change-role");
        User user = userServiceImpl.changeRole(id,changeRoleRequest.getNewRole());
        return ResponseEntity.status(HttpStatus.OK).body("Role changed successfully");
    }

}
