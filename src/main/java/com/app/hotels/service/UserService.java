package com.app.hotels.service;

import com.app.hotels.domain.User;

public interface UserService {

    User create(User user);

    User findByEmail(String email);

}
