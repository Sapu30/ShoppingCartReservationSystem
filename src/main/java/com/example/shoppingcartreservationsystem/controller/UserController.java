package com.example.shoppingcartreservationsystem.controller;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static com.example.shoppingcartreservationsystem.service.UserServiceImpl.hashPassword;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody User user) {
        user.setPassword(hashPassword(user.getPassword()));
        if( Objects.nonNull(userService.findByUserName(user.getUserName())) || userService.findByEmail(user.email)) {
            return ResponseEntity.ok("User already existed");
        }

        User newUser = userService.save(user);
        return ResponseEntity.ok("User '" + newUser.getUserName() +"' created.");

    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        logger.debug("---Getting all users---");
        return userService.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    User readUser(@PathVariable String userName){
        logger.debug("---Reading User '" + userName + "'---");
        User user = userService.findOne(userName);

        if (user != null){
            return user;
        }
        else {
            throw new RuntimeException(String.valueOf(userName));
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userName}")
    ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User input){
        logger.info("---Updating product '" + userName +"'---");
        User user = userService.updateUser(userName,input);

        if(user == null){
            logger.error("---Unable to update product'" + userName +"' not found---");
            throw new RuntimeException();
        }

        User updated = userService.save(input);
        return new ResponseEntity<User>(updated, HttpStatus.OK);

    }


//    @RequestMapping(method = RequestMethod.GET, value = "name/{userName}")
//    User readUser(@PathVariable String userName){
//        logger.debug("---Reading User '" + userName + "'---");
//        User User = userService.getUserByName(userName);
//
//        if (User != null){
//            return User;
//        }
//        else {
//            throw new RuntimeException(String.valueOf(userName));
//        }
//    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    ResponseEntity<?> delete(@PathVariable Long userId){
        if(userService.exists(userId)){
            logger.debug("---Deleting user '"+ userId + "'---");
            userService.deleteUser(userId);
            return ResponseEntity.ok("User '" + userId + "' deleted.");
        }
        else {
            throw new RuntimeException();
        }
    }
}
