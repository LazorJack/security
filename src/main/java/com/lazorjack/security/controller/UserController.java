package com.lazorjack.security.controller;

import com.lazorjack.security.entity.User;
import com.lazorjack.security.service.UserService;
import com.lazorjack.security.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.create(user, false);
    }

}
