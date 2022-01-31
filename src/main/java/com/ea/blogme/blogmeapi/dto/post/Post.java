package com.ea.blogme.blogmeapi.dto.post;

import com.ea.blogme.blogmeapi.dto.category.Category;
import com.ea.blogme.blogmeapi.dto.user.Names;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    private Long id;
    private Long authorId;
    private String title;
    private Names names;
    private String content;
    private Date updatedAt;
    private Date createdAt;
    private String status;
    private List<Category> categoryList;

}
