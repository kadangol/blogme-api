package com.ea.blogme.blogmeapi.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInputDto {
    private Long id;
    private Long blogId;
    private String commentText;
    private Long parentId;
    private Long userId;
}
