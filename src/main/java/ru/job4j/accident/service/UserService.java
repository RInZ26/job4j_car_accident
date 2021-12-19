package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.dao.repository.AuthorityRepository;
import ru.job4j.accident.dao.repository.UserRepository;
import ru.job4j.accident.model.User;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    public boolean saveUser(User user) {
        if (isRegAllowed(user)) {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean isRegAllowed(User user) {
        List<User> byName = userRepository.findByUsername(user.getUsername());
        return byName.isEmpty();
    }
}
