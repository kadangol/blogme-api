package com.ea.blogme.blogmeapi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long authorId;
    @NotBlank
    private String title;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String content;
    @NotBlank
    private String status;
}
