package com.ea.blogme.blogmeapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Names {
    private String firstName;
    private String middleName;
    private String lastName;
}
