package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return userService.getById(id);
    }
}
