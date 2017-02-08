package com.lazorjack.security.service;

import com.lazorjack.security.entity.User;
import com.lazorjack.security.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

/**
 * Created by jacklazorchak on 2/7/17.
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    User user;

    @Before
    public void setUp() {
        user = userService.create(new User("userUser", "password"), false);
    }

    @Test
    public void testCreateUser() {
        Assert.notNull(user);
        Assert.notNull(user.getId());
        Assert.isTrue(user.getRoles().iterator().next().equals(userService.getRoleByName(Constants.USER)));
    }

}
