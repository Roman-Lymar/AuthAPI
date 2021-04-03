package com.springboot.authapiproject.services;


import com.springboot.authapiproject.models.Role;
import com.springboot.authapiproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll(){
        return (List<Role>) roleRepository.findAll();
    }

    public Optional<Role> findRoleById(Integer id){
        return roleRepository.findById(id);
    }
}
