package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.post.BlogResponseDto;
import com.ea.blogme.blogmeapi.dto.post.PostDto;
import com.ea.blogme.blogmeapi.dto.post.PostUpdateDto;
import com.ea.blogme.blogmeapi.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService){
        this.postService=postService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PostDto postDto){
        return postService.add(postDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody PostUpdateDto postDto)
    {
       return postService.update(postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
     return postService.delete(id);
    }

    @PostMapping("/tag")
    public ResponseEntity<?> findAllById(@RequestBody BlogResponseDto responseDto){
        return postService.findAllByIdIn(responseDto);
    }


}
