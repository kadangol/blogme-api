package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.ResponseMessage;
import com.ea.blogme.blogmeapi.dto.comments.Comment;
import com.ea.blogme.blogmeapi.dto.post.*;
import com.ea.blogme.blogmeapi.dto.user.User;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.ICommentService;
import com.ea.blogme.blogmeapi.service.IPostService;
import com.ea.blogme.blogmeapi.service.IUserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class PostProxyService implements IPostService {
    private final String postWithIdUrl = "http://localhost:8082/blog/{id}";
    private final String postUrl = "http://localhost:8082/blog/";

    private final RestTemplate restTemplate;
    private final IUserService userService;
    private final ICommentService commentService;

    public PostProxyService(RestTemplate restTemplate, IUserService userService, ICommentService commentService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return restTemplate.<List<Post>>exchange(postUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    @Override
    public ResponseEntity<?> getPostById(Long id) {
        try {
            PostData postData = new PostData();
            ResponseEntity<Post> post = restTemplate.getForEntity(postWithIdUrl, Post.class, id);
            postData.setPost(post.getBody());

            ResponseEntity<User> user = userService.getById(Objects.requireNonNull(post.getBody())
                    .getAuthorId());
            postData.setUser(user.getBody());

            ResponseEntity<List<Comment>> comments = (ResponseEntity<List<Comment>>) commentService.findByBlogId(post.getBody().getId());
            postData.setComments(comments.getBody());
            return ResponseEntity.ok(postData);
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
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BlogResponseDto> requestEntity = new HttpEntity<>(responseDto, headers);
            return restTemplate.<List<Post>>exchange(postUrl+"/tag", HttpMethod.POST, requestEntity,
                    new ParameterizedTypeReference<>() {});
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> getPostByAuthorId(Long id) {
        try {
            return restTemplate.<List<Post>>exchange(postUrl+"/author/"+id, HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {});
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> deleteAllByAuthorId(Long id) {
        try {
            return restTemplate.exchange(postUrl+"/author/"+id, HttpMethod.DELETE, null, ResponseMessage.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }


}
