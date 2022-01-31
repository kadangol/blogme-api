package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.post.BlogResponseDto;
import com.ea.blogme.blogmeapi.dto.post.PostDto;
import com.ea.blogme.blogmeapi.dto.post.PostUpdateDto;
import org.springframework.http.ResponseEntity;

public interface IPostService {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getPostById( Long id);

    ResponseEntity<?> add( PostDto postDto);

    ResponseEntity<?> update(PostUpdateDto postUpdateDto);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> findAllByIdIn(BlogResponseDto responseDto);
}
