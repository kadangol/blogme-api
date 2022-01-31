package com.ea.blogme.blogmeapi.service;

import com.ea.blogme.blogmeapi.dto.category.CategoryDto;
import com.ea.blogme.blogmeapi.dto.category.CategoryUpdateDto;
import com.ea.blogme.blogmeapi.dto.post.BlogResponseDto;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> getAll();
    ResponseEntity<?> add(CategoryDto categoryDto);
    ResponseEntity<?> update(CategoryUpdateDto categoryUpdateDto);
    ResponseEntity<?> delete(Long id);

}
