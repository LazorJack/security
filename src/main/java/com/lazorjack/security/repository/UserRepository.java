package com.lazorjack.security.repository;

import com.lazorjack.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jacklazorchak on 2/7/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
