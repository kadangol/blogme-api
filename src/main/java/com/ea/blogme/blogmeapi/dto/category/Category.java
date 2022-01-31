package com.ea.blogme.blogmeapi.dto.category;

import com.ea.blogme.blogmeapi.dto.post.Post;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private Long id;
    private String title;
    private String content;
    private List<Post> postList;
}
