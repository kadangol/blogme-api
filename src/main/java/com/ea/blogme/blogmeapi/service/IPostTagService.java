package com.ea.blogme.blogmeapi.service;

import org.springframework.http.ResponseEntity;

public interface IPostTagService {
    ResponseEntity<?> findAllBlogByTagTitle(String title);

    ResponseEntity<?> findAllBlogByTagId(long id);
}
