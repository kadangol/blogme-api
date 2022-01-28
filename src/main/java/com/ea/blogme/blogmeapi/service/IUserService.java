package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(Long id);
}
