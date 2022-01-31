package com.ea.blogme.blogmeapi.service.impl;

import com.ea.blogme.blogmeapi.dto.ResponseMessage;
import com.ea.blogme.blogmeapi.dto.category.Category;
import com.ea.blogme.blogmeapi.dto.category.CategoryDto;
import com.ea.blogme.blogmeapi.dto.category.CategoryUpdateDto;
import com.ea.blogme.blogmeapi.dto.user.User;
import com.ea.blogme.blogmeapi.exceptionhandler.exception.CustomException;
import com.ea.blogme.blogmeapi.service.ICategoryService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryProxyService implements ICategoryService {
    private final String categoryWithIdUrl = "http://localhost:8082/category/{id}";
    private final String categoryUrl = "http://localhost:8082/category/";

    private final RestTemplate restTemplate;

    public CategoryProxyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return restTemplate.<List<User>>exchange(categoryUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            return restTemplate.getForEntity(categoryWithIdUrl, Category.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }    }

    @Override
    public ResponseEntity<?> add(CategoryDto categoryDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CategoryDto> requestEntity = new HttpEntity<>(categoryDto, headers);
        try{
            return restTemplate.postForEntity(categoryUrl, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> update(CategoryUpdateDto categoryUpdateDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CategoryUpdateDto> requestEntity = new HttpEntity<>(categoryUpdateDto, headers);
        try{
            return restTemplate.exchange(categoryUrl, HttpMethod.PUT, requestEntity, ResponseMessage.class);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return restTemplate.exchange(categoryWithIdUrl, HttpMethod.DELETE, null, ResponseMessage.class, id);
        }
        catch (HttpStatusCodeException e){
            throw new CustomException(e.getMessage(), e);
        }
    }

}

