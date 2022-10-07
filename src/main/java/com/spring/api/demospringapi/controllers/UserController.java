package com.spring.api.demospringapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;

import com.spring.api.demospringapi.services.UserService;
import com.spring.api.demospringapi.models.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
     @Autowired
     private UserService userService;
     
     @GetMapping
     public List<User> getAllUsers() {
    	 
         return userService.findAllUsers();
     }
     
     @GetMapping("/{id}")
     public ResponseEntity<User> getUserById(@PathVariable Long id) {
    	 
         return userService.findUserById(id)
                 .map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
     } 
     
     @PutMapping("/{id}")
     public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
         return userService.findUserById(id)
                 .map(userObj -> {
                     userObj.setId(id);
                     return ResponseEntity.ok(userService.updateUser(userObj));
                 })
                 .orElseGet(() -> ResponseEntity.notFound().build());
     }
     
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public User createUser(@RequestBody @Validated User user) {
         return userService.createUser(user);
     }
     
     @DeleteMapping("/{id}")
     public ResponseEntity<User> deleteUser(@PathVariable Long id) {
         return userService.findUserById(id)
                 .map(user -> {
                     userService.deleteUserById(id);
                     return ResponseEntity.ok(user);
                 })
                 .orElseGet(() -> ResponseEntity.notFound().build());
     }
     
}
