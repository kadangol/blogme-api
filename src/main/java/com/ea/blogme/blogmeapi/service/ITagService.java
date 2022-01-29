package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.tag.BlogResponse;
import com.ea.blogme.blogmeapi.dto.tag.TagDelete;
import com.ea.blogme.blogmeapi.dto.tag.TagSave;
import org.springframework.http.ResponseEntity;

public interface ITagService {
    void save(TagSave dto);

    BlogResponse findAllBlogIdByTagTitle(String title);

    BlogResponse findAllBlogIdByTagId(long id);

    void deleteFromBlogTagByTagIdAndBlogId(TagDelete tagDelete);
}
