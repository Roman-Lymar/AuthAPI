package com.springboot.authapiproject.services;


import com.springboot.authapiproject.models.Role;
import com.springboot.authapiproject.repositories.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LogManager.getLogger(RoleRepository.class.getSimpleName());


    public List<Role> findAll(){

        logger.info("Method findAll called");
        return (List<Role>) roleRepository.findAll();
    }

    public Optional<Role> findRoleById(Integer id){
        logger.info("Method findRoleById called");
        return roleRepository.findById(id);
    }

    public Role findRoleByName(String role){
        logger.info("Method findRoleByName called");
        return roleRepository.findByRole(role);
    }
}
