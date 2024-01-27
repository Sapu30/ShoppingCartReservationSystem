package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.controller.ProductController;
import com.example.shoppingcartreservationsystem.controller.UserController;
import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final byte[] secret = "rpibnmyfnqkkmnfwveghyaabzmorpybc".getBytes();

    public Map<String, Object> login(User user) throws NoSuchElementException {
        List<User> users = userRepository.fetchAllUser();
        User filteredUser = users.stream()
                .filter(u -> u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword()))
                .findFirst().orElse(null);

        if (!Objects.nonNull(filteredUser)) {
            logger.error("No User Found");
            throw new NoSuchElementException("No User found");
        }

        return this.createToken(user);
    }

    private Map<String, Object> createToken(User user) {

        final JwtClaims claims = new JwtClaims();
        claims.setSubject(user.toString());
        claims.setExpirationTimeMinutesInTheFuture(Duration.ofHours(1).toMinutes());

        final JsonWebSignature signature = new JsonWebSignature();
        signature.setKey(new HmacKey(this.secret));
        signature.setPayload(claims.toJson());
        signature.setAlgorithmHeaderValue("HS256");

        try {
            final Map<String, Object> map = new HashMap<>(2);
            map.put("token", signature.getCompactSerialization());
            map.put("expire", claims.getExpirationTime());

            return map;
        } catch (JoseException | MalformedClaimException e) {
            throw new SecurityException(e);
        }
    }


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
