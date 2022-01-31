package com.ea.blogme.blogmeapi.controller;

import com.ea.blogme.blogmeapi.dto.category.CategoryDto;
import com.ea.blogme.blogmeapi.dto.category.CategoryUpdateDto;
import com.ea.blogme.blogmeapi.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private  final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody  CategoryDto categoryDto){
        return categoryService.add(categoryDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryUpdateDto categoryDto){
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
         return categoryService.delete(id);
    }

}