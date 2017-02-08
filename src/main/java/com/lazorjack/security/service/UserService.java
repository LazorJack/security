package com.lazorjack.security.service;

import com.lazorjack.security.entity.Role;
import com.lazorjack.security.entity.User;
import com.lazorjack.security.repository.RoleRepository;
import com.lazorjack.security.repository.UserRepository;
import com.lazorjack.security.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jacklazorchak on 2/7/17.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getByUsername(s);

        if(user == null) {
            throw new UsernameNotFoundException("User does not exist with username: " + s);
        }

        return user;
    }

    public User create(User user, boolean isAdmin) {
        if(user.getId() != null) {
            throw new RuntimeException("Cannot create user with an id");
        }

        if(user.getPassword() == null) {
            throw new RuntimeException("Cannot create user with an id");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Role role;
        if(isAdmin) {
            role = getRoleByName(Constants.ADMIN);
        } else {
            role = getRoleByName(Constants.USER);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User update(User user) {
        if(user.getId() == null) {
            throw new RuntimeException("Cannot update user without an id");
        }

        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    private Role createRole(Role role) {
        return roleRepository.save(role);
    }

    private Role updateRole(Role role) {
        if(role.getId() == null) {
            throw new RuntimeException("Cannot update role without an id");
        }
        return roleRepository.save(role);
    }

    @PostConstruct
    private void initialize() {
        createRolesIfNecessary();
        createAdmin();
    }

    private void createRolesIfNecessary() {
        Role admin = getRoleByName(Constants.ADMIN);
        if(admin == null) {
            log.info("Creating admin role");
            createRole(new Role(Constants.ADMIN, Constants.ADMIN));
        }
        Role user = getRoleByName(Constants.USER);
        if(user == null) {
            log.info("Creating user role");
            createRole(new Role(Constants.USER, Constants.USER));
        }
    }

    private void createAdmin() {
        if(getByUsername(Constants.ADMIN_USERNAME) == null) {
            create(new User(Constants.ADMIN_USERNAME, Constants.ADMIN_PASSWORD), true);
        }
    }

}
