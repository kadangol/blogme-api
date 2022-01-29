package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.ResponseMessage;
import com.ea.blogme.blogmeapi.dto.user.User;
import com.ea.blogme.blogmeapi.dto.user.UserSave;
import com.ea.blogme.blogmeapi.dto.user.UserUpdate;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserProxyService implements IUserService {
    private final String userWithIdUrl = "http://localhost:8081/user/{id}";
    private final String userUrl = "http://localhost:8081/user/";

    private final RestTemplate restTemplate;

    public UserProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return restTemplate.<List<User>>exchange(userUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            return restTemplate.getForEntity(userWithIdUrl, User.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> save(UserSave userSave) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserSave> requestEntity = new HttpEntity<>(userSave, headers);
        try{
            return restTemplate.postForEntity(userUrl, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> update(UserUpdate userUpdate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserUpdate> requestEntity = new HttpEntity<>(userUpdate, headers);
        try{
            return restTemplate.exchange(userUrl, HttpMethod.PUT, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return restTemplate.exchange(userWithIdUrl, HttpMethod.DELETE, null, ResponseMessage.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }
}
