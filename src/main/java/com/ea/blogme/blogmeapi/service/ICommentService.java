package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.comments.CommentInputDto;
import com.ea.blogme.blogmeapi.dto.comments.CommentUpdateDto;
import org.springframework.http.ResponseEntity;

public interface ICommentService {
    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> save(CommentInputDto commentInputDto);

    ResponseEntity<?> update(CommentUpdateDto commentUpdateDto, Long commentId);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> findByBlogId(Long blogId);
}
