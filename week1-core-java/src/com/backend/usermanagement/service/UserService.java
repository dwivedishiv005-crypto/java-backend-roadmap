package com.backend.usermanagement.service;

import com.backend.usermanagement.exception.UserAlreadyExistsException;
import com.backend.usermanagement.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<Long, User> userStore = new HashMap<>();

    public void addUser(User user) {
        if (userStore.containsKey(user.getId())) {
            throw new UserAlreadyExistsException(
                    "User with id " + user.getId() + " already exists"
            );
        }
        userStore.put(user.getId(), user);
    }

    public User getUserById(Long id) {
        return userStore.get(id);
    }

    public Map<Long, User> getAllUsers() {
        return new HashMap<>(userStore);
    }
}
