package com.lazorjack.security.repository;

import com.lazorjack.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jacklazorchak on 2/7/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
