package com.springboot.authapiproject.services;


import com.springboot.authapiproject.config.jwt.JwtProvider;
import com.springboot.authapiproject.exceptions.ErrorMessages;
import com.springboot.authapiproject.exceptions.ResourceNotFoundException;
import com.springboot.authapiproject.models.Role;
import com.springboot.authapiproject.models.User;
import com.springboot.authapiproject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessages.NO_RESOURCE_FOUND.getErrorMessage() + id)));
    }

    public List<User> getUsersByName(String name) {
        Iterable<User> packageCustoms = userRepository.findAll();
        List<User> resultList = new ArrayList<>();

        for(User p: packageCustoms){
            if(p.getLogin().contains(name)){
                resultList.add(p);
            }
        }

        return resultList;
    }

    public User registerUser(User user) throws Exception {
        if(findUserByLogin(user.getLogin())==null){
            Role role = roleService.findRoleById(2).get();
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info(user.getPassword());
            return userRepository.save(user);
        }
        else{
            throw new Exception("User with this login already exist");
        }

    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        if(getUserById(id).isPresent()) {
            userRepository.deleteById(id);
        }

    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findUserByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public User changePassword(User user, String password, String newPassword) throws Exception {
        if(passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return saveUser(user);
        }
        else{
            throw new Exception("passwords don't match");
        }
    }

    @Autowired
    private JwtProvider jwtProvider;

    public Boolean compareId(String header, UUID id){
        String token = header.substring(7);
        return jwtProvider.getIdFromToken(token).equals(id.toString());
    }
}
