package com.ea.blogme.blogmeapi.dto.post;

import com.ea.blogme.blogmeapi.dto.comments.Comment;
import com.ea.blogme.blogmeapi.dto.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostData {
    private Post post;
    private User user;
    private List<Comment> comments;
}
