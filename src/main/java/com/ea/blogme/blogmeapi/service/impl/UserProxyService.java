package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.user.User;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<List<User>> response = restTemplate.exchange(userUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        return response;
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
}
