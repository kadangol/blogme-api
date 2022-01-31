package com.ea.blogme.blogmeapi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
    private Long id;
    private String title="";
    private String firstName="";
    private String middleName="";
    private String lastName="";
    private String content="";
    private String status="";

}
