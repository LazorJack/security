package com.lazorjack.security.controller;

import com.lazorjack.security.entity.User;
import com.lazorjack.security.service.UserService;
import com.lazorjack.security.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jacklazorchak on 2/7/17.
 */
@RestController
@RequestMapping(Constants.USER_ENDPOINT)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user, false), HttpStatus.CREATED);
    }

}
