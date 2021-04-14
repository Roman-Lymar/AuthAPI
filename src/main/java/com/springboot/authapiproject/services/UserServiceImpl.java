package com.springboot.authapiproject.services;


import com.springboot.authapiproject.config.jwt.JwtProvider;
import com.springboot.authapiproject.exceptions.*;
import com.springboot.authapiproject.models.Role;
import com.springboot.authapiproject.models.User;
import com.springboot.authapiproject.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getSimpleName());

    public List<User> findAll(){
        logger.info("Method findAll called");
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        logger.info("Method getUserById called");
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorMessages.NOT_FOUND_USER.getErrorMessage() + id)));
    }

    public List<User> getUsersByName(String name) {
        logger.info("Method getUsersByName called");
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
        logger.info("Method registerUser called");
        if(findUserByLogin(user.getLogin())==null){
            Role role = roleService.findRoleById(1).get();
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        else{
            throw new ConflictException(ErrorMessages.USER_EXISTS.getErrorMessage());
        }
    }

    public User saveUser(User user){
        logger.info("Method saveUser called");
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        logger.info("Method deleteUser called");
        if(getUserById(id).isPresent()) {
            userRepository.deleteById(id);
        }

    }

    public User findUserByLogin(String login){
        logger.info("Method findUserByLogin called");
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        logger.info("Method findByLoginAndPassword called");
        User userEntity = findUserByLogin(login);
        if (userEntity != null) {
           if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }else throw new ResourceNotFoundException(ErrorMessages.INCORRECT.getErrorMessage());
        }
        else throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND_USER.getErrorMessage());

    }

    public User changePassword(UUID id, String password, String newPassword) throws Exception {
        logger.info("Method changePassword called");
        User user = getUserById(id).get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            if (passwordEncoder.matches(newPassword, user.getPassword())) {
                throw new Exception("Passwords shouldn't match");
            } else {
                user.setPassword(passwordEncoder.encode(newPassword));
                return saveUser(user);
            }
        } else {
            throw new Exception("Passwords don't match");
        }
    }

    public User changeLogin(UUID id, String newLogin) throws Exception {
        logger.info("Method changeLogin called");
        if(newLogin.length()<2){
           throw new EmptyFieldException(ErrorMessages.EMPTY_FIELD.getErrorMessage());
        }else {
            User user = getUserById(id).get();
            user.setLogin(newLogin);
            saveUser(user);
            return user;
        }
    }

    public User changeRole(UUID id, String newRole){
        logger.info("Method changeRole called");
        User user = getUserById(id).get();
        Role role = roleService.findRoleByName(newRole);
        if(newRole.equals("client") || newRole.equals("admin")){
            logger.info(role.getName());
            user.setRole(role);
            saveUser(user);
            return user;
           }else throw new InvalidRole(ErrorMessages.INVALID_ROLE.getErrorMessage());
    }



    @Autowired
    private JwtProvider jwtProvider;

    public Boolean compareId(String header, UUID id){
        logger.info("Method compareId called");
        String token = header.substring(7);
        return jwtProvider.getIdFromToken(token).equals(id.toString());
    }
}
