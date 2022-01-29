package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.tag.BlogResponse;
import com.ea.blogme.blogmeapi.dto.tag.TagDelete;
import com.ea.blogme.blogmeapi.dto.tag.TagSave;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.ITagService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class TagProxyService implements ITagService {
    private final String tagUrl = "http://localhost:8085/tag";
    private final String tagFilterBlogsByTitle = "http://localhost:8085/tag/title/blogs?title=";
    private final String tagFilterBlogsByTagId = "http://localhost:8085/tag/id/blogs?tagId=";

    private final RestTemplate restTemplate;

    public TagProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(TagSave dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TagSave> requestEntity = new HttpEntity<>(dto, headers);
        try{
            restTemplate.postForLocation(tagUrl, requestEntity);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public BlogResponse findAllBlogIdByTagTitle(String title) {
        ResponseEntity<BlogResponse> response =  restTemplate.exchange(tagFilterBlogsByTitle + title, HttpMethod.GET, null,
                BlogResponse.class);
        return response.getBody();
    }

    @Override
    public BlogResponse findAllBlogIdByTagId(long id) {
        ResponseEntity<BlogResponse> response =  restTemplate.exchange(tagFilterBlogsByTagId + id, HttpMethod.GET, null,
                BlogResponse.class);
        return response.getBody();
    }

    @Override
    public void deleteFromBlogTagByTagIdAndBlogId(TagDelete tagDelete) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TagDelete> requestEntity = new HttpEntity<>(tagDelete, headers);
        try{
            restTemplate.delete(tagUrl, requestEntity);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }
}
