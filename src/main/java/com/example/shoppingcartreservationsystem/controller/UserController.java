package com.example.shoppingcartreservationsystem.controller;

import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
//    BCryptPasswordEncoder bCryptPasswordEncoder

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody User user) {
//        User.setPassword(bCryptPasswordEncoder.encode(User.getPassword()));
        if(userService.findByUserName(user.getUserName()) != null){
            throw new RuntimeException(user.getUserName());
        }
        else if(userService.findByEmail(user.email) != null){
            throw new RuntimeException(user.email);
        }
        else {
            User newUser = userService.save(user);
            return ResponseEntity.ok("User '" + newUser.getUserName() +"' created.");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        logger.debug("---Getting all users---");
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User input){
        logger.debug("---Adding new user '" + input.lastName +" " + input.firstName +"'---");
        User result = userService.save(
                new User(
                        input.getUserName(),
                        input.firstName,
                        input.lastName,
                        input.email,
                        input.getPassword()
                ));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(result.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    User readUser(@PathVariable Long userId){
        logger.debug("---Reading User '" + userId + "'---");
        User User = userService.findOne(userId);

        if (User != null){
            return User;
        }
        else {
            throw new RuntimeException(String.valueOf(userId));
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    ResponseEntity<?> delete(@PathVariable Long userId){
        if(userService.exists(userId)){
            logger.debug("---Deleting user '"+ userId + "'---");
            return ResponseEntity.ok("User '" + userId + "' deleted.");
        }
        else {
            throw new RuntimeException();
        }
    }
}
