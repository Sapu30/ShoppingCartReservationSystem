package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> login(User user);

    User findByUserName(String userName);

    Boolean findByEmail(String email);

    User save(User user);

    List<User> findAll();


    User findOne(String userName);

//    User findByName(String userName);

    boolean exists(Long userId);

    void deleteUser(Long userId);

    User updateUser(String userName, User input);
}
