package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.user.UserSave;
import com.ea.blogme.blogmeapi.dto.user.UserUpdate;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> save(UserSave userSave);

    ResponseEntity<?> update(UserUpdate userUpdate);

    ResponseEntity<?> delete(Long id);
}
