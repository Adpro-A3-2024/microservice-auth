package id.ac.ui.cs.youkosu.microservice_auth.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserDTO {
    private String username;
    private String password;
    private String name;
    private String address;
}
