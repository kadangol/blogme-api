package com.ea.blogme.blogmeapi.dto.user;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private Names names;
    private String mobile;
    private String email;
    private String password;
    private Date registeredAt;
    private String intro;
    private String profile;
    private List<Role> roles;
}
