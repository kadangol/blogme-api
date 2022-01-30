package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.comments.CommentInputDto;
import com.ea.blogme.blogmeapi.dto.comments.CommentUpdateDto;
import com.ea.blogme.blogmeapi.dto.user.UserSave;
import com.ea.blogme.blogmeapi.dto.user.UserUpdate;
import com.ea.blogme.blogmeapi.service.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final ICommentService commentService;

    public CommentController(ICommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return commentService.getById(id);
    }

    @GetMapping("/blog/{blogId}")
    public ResponseEntity<?> findByBlogId(@PathVariable Long blogId){
        return commentService.findByBlogId(blogId);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CommentInputDto commentInputDto){
        return commentService.save(commentInputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long id){
        return commentService.update(commentUpdateDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return commentService.delete(id);
    }
}
