package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.User;

public interface UserDao {
    boolean save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}