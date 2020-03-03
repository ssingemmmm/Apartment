package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.User;

public interface UserService {
    boolean save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
    boolean deleteUserById(Integer id);
}
