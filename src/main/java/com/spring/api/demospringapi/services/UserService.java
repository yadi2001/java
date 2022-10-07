package com.spring.api.demospringapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.spring.api.demospringapi.repositories.UserRepository;

import  com.spring.api.demospringapi.models.User;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String email, String password) {
        return userRepository.login(email, password);
    }

    public User createUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            //throw new UserRegistrationException("User with email "+ user.getEmail()+" already exists");
        }
        return userRepository.save(user);
    }
    
    
    /*
    public User createUser(User user) {
        return userRepository.save(user);
    }
    */
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
