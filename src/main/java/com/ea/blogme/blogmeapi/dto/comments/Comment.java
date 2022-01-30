package com.ea.blogme.blogmeapi.dto.comments;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    private Long id;
    private Long blogId;
    private String commentText;
    private Date createdDate;
    private Date modifiedDate;
    private Set<Comment> childComment = new HashSet<Comment>();
}
