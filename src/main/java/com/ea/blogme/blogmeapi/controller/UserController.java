package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.user.UserSave;
import com.ea.blogme.blogmeapi.dto.user.UserUpdate;
import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserSave userSave){
        return userService.save(userSave);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdate userUpdate){
        return userService.update(userUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return userService.delete(id);
    }
}
