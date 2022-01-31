package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.post.BlogResponseDto;
import com.ea.blogme.blogmeapi.dto.tag.BlogResponse;
import com.ea.blogme.blogmeapi.service.IPostService;
import com.ea.blogme.blogmeapi.service.IPostTagService;
import com.ea.blogme.blogmeapi.service.ITagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostTagService implements IPostTagService {
    private final ITagService tagService;
    private final IPostService postService;

    public PostTagService(ITagService tagService, IPostService postService) {
        this.tagService = tagService;
        this.postService = postService;
    }

    @Override
    public ResponseEntity<?> findAllBlogByTagTitle(String title) {
        BlogResponse response = tagService.findAllBlogIdByTagTitle(title);
        return postService.findAllByIdIn(new BlogResponseDto(response.getBlogId()));
    }

    @Override
    public ResponseEntity<?> findAllBlogByTagId(long id) {
        return null;
    }
}
