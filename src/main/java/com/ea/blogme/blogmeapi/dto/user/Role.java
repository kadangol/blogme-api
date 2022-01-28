package com.ea.blogme.blogmeapi.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Role {
    private Long id;
    private String role;

    public Role(String role){
        this.role = role;
    }
}
