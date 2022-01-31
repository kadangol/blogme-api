package com.ea.blogme.blogmeapi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDto {
    private List<Long> ids = new ArrayList<>();
}
