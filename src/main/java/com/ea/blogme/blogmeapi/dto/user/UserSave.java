package com.ea.blogme.blogmeapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSave {
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String mobile;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String intro="";
    private String profile="";

    private List<Long> roles = new ArrayList<>();
}
