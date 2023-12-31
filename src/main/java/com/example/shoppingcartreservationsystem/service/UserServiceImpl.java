package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

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


    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);

        return user;
    }

    public Boolean findByEmail(String email) {
        User users = userRepository.findByEmail(email);
        if (users == null) {
            return false;
        }
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public User findOne(Long userId) {
        List<User> users = userRepository.findByUserId(userId);
        if (users.isEmpty()) {
            throw new RuntimeException("Product with ID " + userId + " not found");
        }
        return users.get(0);
    }

    public User findOne(String userName) {
        User user = userRepository.findByUserName(userName);
        if (!Objects.nonNull(user)) {
            throw new RuntimeException("Product with userName " + userName + " not found");
        }
        return user;
    }


    @Override
    public User updateUser(String userName, User user) {
        User value = userRepository.findByUserName(userName);
        if (userName.equals(value.getUserName())) {
            user.setUserId(value.getUserId());
            return userRepository.save(value);
        }
        return null;
    }


    public boolean exists(Long userId) {
        return true;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public static String hashPassword(String password) {
        try {
            // Create a MessageDigest with SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the password string to bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Update the digest with the password bytes
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(String.format("%02x", hashedByte));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
