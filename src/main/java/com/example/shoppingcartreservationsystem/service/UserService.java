package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.User;

import java.util.List;

public interface UserService {

    Boolean findByUserName(String userName);

    Boolean findByEmail(String email);

    User save(User user);

    List<User> findAll();

    User findOne(Long userId);

    boolean exists(Long userId);
}
