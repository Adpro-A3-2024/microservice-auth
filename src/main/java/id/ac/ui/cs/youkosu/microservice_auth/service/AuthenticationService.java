package id.ac.ui.cs.youkosu.microservice_auth.service;


import id.ac.ui.cs.youkosu.microservice_auth.DTO.LoginUserDTO;
import id.ac.ui.cs.youkosu.microservice_auth.DTO.RegisterUserDTO;
import id.ac.ui.cs.youkosu.microservice_auth.model.Role;
import id.ac.ui.cs.youkosu.microservice_auth.model.RoleEnum;
import id.ac.ui.cs.youkosu.microservice_auth.model.User;
import id.ac.ui.cs.youkosu.microservice_auth.repository.RoleRepository;
import id.ac.ui.cs.youkosu.microservice_auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository, RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            return null;
        }
        User user = User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .address(input.getAddress())
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }

    public User signupAdmin(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            return null;
        }
        User user = User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .address(input.getAddress())
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}