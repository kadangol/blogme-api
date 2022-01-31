package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.tag.TagDelete;
import com.ea.blogme.blogmeapi.dto.tag.TagSave;
import com.ea.blogme.blogmeapi.service.IPostTagService;
import com.ea.blogme.blogmeapi.service.ITagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final ITagService tagService;
    private final IPostTagService postTagService;

    public TagController(ITagService tagService, IPostTagService postTagService){
        this.tagService = tagService;
        this.postTagService = postTagService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TagSave dto){
        tagService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/title/blogs")
    public ResponseEntity<?> findAllBlogByTagTitle(@RequestParam String title){
        return postTagService.findAllBlogByTagTitle(title);
    }

    @GetMapping("/id/blogs")
    public ResponseEntity<?> findAllBlogByTagId(@RequestParam Long tagId){
        return postTagService.findAllBlogByTagId(tagId);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromBlogTagByTagIdAndBlogId(@RequestBody TagDelete tagDelete){
        tagService.deleteFromBlogTagByTagIdAndBlogId(tagDelete);
        return ResponseEntity.ok().build();
    }
}
