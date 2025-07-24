package com.basma.Demo1.user.dtos;

import com.basma.Demo1.user.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userdto {
    @NotBlank(message = "Firstname should not be blank")
    @Size(min = 3, max = 10, message = "firstname length invalid")
    private String firstname;
    @NotBlank(message = "Lastname should not be blank")
    @Size(min = 3, max = 10, message = "lastname length invalid")
    private String lastname;
    @NotBlank(message = "Email should not be blank")
    @Email(message ="Email invalid")
    private String email;
    @NotNull(message = "Role should not be blank")
    private Role role;
    @NotNull(message = "password should not be null")
    @Size(min = 6, max = 12, message = "password length invalid")
    private String password;

}
