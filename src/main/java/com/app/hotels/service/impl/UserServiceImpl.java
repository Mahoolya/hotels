package com.app.hotels.service.impl;

import com.app.hotels.domain.User;
import com.app.hotels.domain.exception.ResourceAlreadyExistsException;
import com.app.hotels.domain.exception.ResourceDoesNotExistException;
import com.app.hotels.repository.UserRepository;
import com.app.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no user with email " + email));
    }

}
