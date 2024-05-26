package id.ac.ui.cs.youkosu.microservice_auth.controller;

import id.ac.ui.cs.youkosu.microservice_auth.DTO.LoginResponse;
import id.ac.ui.cs.youkosu.microservice_auth.DTO.LoginUserDTO;
import id.ac.ui.cs.youkosu.microservice_auth.DTO.RegisterUserDTO;
import id.ac.ui.cs.youkosu.microservice_auth.model.User;
import id.ac.ui.cs.youkosu.microservice_auth.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JWTService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JWTService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        System.out.println(registerUserDto.getUsername());
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/admin-signup")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterUserDTO registerUserDto) {
        System.out.println(registerUserDto.getUsername());
        User registeredUser = authenticationService.signupAdmin(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}