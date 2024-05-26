package id.ac.ui.cs.youkosu.microservice_auth.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserDTO {
    private String username;
    private String password;
}