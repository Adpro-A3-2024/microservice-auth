package id.ac.ui.cs.youkosu.microservice_auth.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponse {
    private String token;

    private long expiresIn;

}