package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public User getUserName(String userName) {
//        List<User> users = (List<User>) userRepository.findByUserName(userName);
//        if (users.isEmpty()) {
//            throw new RuntimeException("Product with ID " + userName + " not found");
//        }
//        return users.get(0);
//    }
//
//    public User getEmail(String email) {
//        List<User> users = (List<User>) userRepository.findByEmail(email);
//        if (users.isEmpty()) {
//            throw new RuntimeException("Product with ID " + email + " not found");
//        }
//        return users.get(0);
//    }


    public Boolean findByUserName(String userName) {
        User users = userRepository.findByUserName(userName);
        if (users == null) {
            throw new RuntimeException("Product with ID " + userName + " not found");
        }
        return true;
    }

    public Boolean findByEmail(String email) {
        User users = userRepository.findByEmail(email);
        if (users == null) {
            throw new RuntimeException("Product with ID " + email + " not found");
        }
        return true;
    }

}
