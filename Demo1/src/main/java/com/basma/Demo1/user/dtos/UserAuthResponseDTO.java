package com.basma.Demo1.user.dtos;

import com.basma.Demo1.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAuthResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private String token;
}
