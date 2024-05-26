package id.ac.ui.cs.youkosu.microservice_auth.service;

import id.ac.ui.cs.youkosu.microservice_auth.model.User;
import id.ac.ui.cs.youkosu.microservice_auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }
}