package dev.vikel.taskmanagement.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    //Login data tranfer object.
    private String email;
    private String password;
}
