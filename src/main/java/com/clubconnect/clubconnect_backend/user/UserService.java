package com.clubconnect.clubconnect_backend.user;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getAllUsers();
}