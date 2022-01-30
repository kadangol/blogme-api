package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.ResponseMessage;
import com.ea.blogme.blogmeapi.dto.comments.Comment;
import com.ea.blogme.blogmeapi.dto.comments.CommentInputDto;
import com.ea.blogme.blogmeapi.dto.comments.CommentUpdateDto;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.ICommentService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    private final String commentUrl = "http://localhost:8083/comment/";

    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return restTemplate.<List<Comment>>exchange(commentUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public ResponseEntity<Comment> getById(Long id) {
        try {
            return restTemplate.getForEntity(commentUrl + "{id}", Comment.class, id);
        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> save(CommentInputDto commentInputDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CommentInputDto> requestEntity = new HttpEntity<>(commentInputDto, headers);
        try{
            var result = restTemplate.postForEntity(commentUrl, requestEntity, Comment.class);
            return new ResponseEntity(result.getBody(), HttpStatus.OK);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> update(CommentUpdateDto commentUpdateDto, Long commentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CommentUpdateDto> requestEntity = new HttpEntity<>(commentUpdateDto, headers);
        try{
            var result = restTemplate.exchange(commentUrl +"{id}", HttpMethod.PUT, requestEntity, Comment.class, commentId.toString());
            return new ResponseEntity(result.getBody(), HttpStatus.OK);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return restTemplate.exchange(commentUrl+"{id}", HttpMethod.DELETE, null, String.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> findByBlogId(Long blogId) {
        try {
            var result = (List<Comment>) restTemplate.getForObject(commentUrl + "blog/{blogId}", List.class, blogId.toString());
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (HttpStatusCodeException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }
}
