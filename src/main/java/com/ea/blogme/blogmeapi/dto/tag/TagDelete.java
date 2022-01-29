package com.ea.blogme.blogmeapi.dto.tag;

import lombok.Data;

@Data
public class TagDelete {
    private Long blogId;
    private Long tagId;
}
