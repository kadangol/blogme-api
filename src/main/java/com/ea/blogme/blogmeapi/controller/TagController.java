package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.tag.BlogResponse;
import com.ea.blogme.blogmeapi.dto.tag.TagDelete;
import com.ea.blogme.blogmeapi.dto.tag.TagSave;
import com.ea.blogme.blogmeapi.service.ITagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final ITagService tagService;

    public TagController(ITagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TagSave dto){
        tagService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/title/blogs")
    public ResponseEntity<BlogResponse> findAllBlogByTagTitle(@RequestParam String title){
        return ResponseEntity.ok(tagService.findAllBlogIdByTagTitle(title));
    }

    @GetMapping("/id/blogs")
    public ResponseEntity<BlogResponse> findAllBlogByTagId(@RequestParam Long tagId){
        return ResponseEntity.ok(tagService.findAllBlogIdByTagId(tagId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromBlogTagByTagIdAndBlogId(@RequestBody TagDelete tagDelete){
        tagService.deleteFromBlogTagByTagIdAndBlogId(tagDelete);
        return ResponseEntity.ok().build();
    }
}
