package com.ea.blogme.blogmeapi.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}

