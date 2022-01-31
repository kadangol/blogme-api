package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.ResponseMessage;
import com.ea.blogme.blogmeapi.dto.post.BlogResponseDto;
import com.ea.blogme.blogmeapi.dto.post.Post;
import com.ea.blogme.blogmeapi.dto.post.PostDto;
import com.ea.blogme.blogmeapi.dto.post.PostUpdateDto;
import com.ea.blogme.blogmeapi.dto.user.User;
import com.ea.blogme.blogmeapi.dto.user.UserSave;
import com.ea.blogme.blogmeapi.dto.user.UserUpdate;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.IPostService;
import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostProxyService implements IPostService {
    private final String postWithIdUrl = "http://localhost:8082/blog/{id}";
    private final String postUrl = "http://localhost:8082/blog/";

    private final RestTemplate restTemplate;

    public PostProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return restTemplate.<List<User>>exchange(postUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    @Override
    public ResponseEntity<?> getPostById(Long id) {
        try {
            return restTemplate.getForEntity(postWithIdUrl, Post.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }    }

    @Override
    public ResponseEntity<?> add(PostDto postDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostDto> requestEntity = new HttpEntity<>(postDto, headers);
        try{
            return restTemplate.postForEntity(postUrl, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> update(PostUpdateDto postUpdateDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostUpdateDto> requestEntity = new HttpEntity<>(postUpdateDto, headers);
        try{
            return restTemplate.exchange(postUrl, HttpMethod.PUT, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return restTemplate.exchange(postWithIdUrl, HttpMethod.DELETE, null, ResponseMessage.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> findAllByIdIn(BlogResponseDto responseDto) {
        return null;
    }


}
