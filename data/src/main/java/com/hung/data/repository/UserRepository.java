/**
 * ****************************************************
 * * Description :
 * * File        : UserRepository.java
 * * Author      : hung.tran
 * * Date        : Nov 12, 2020
 * ****************************************************
 **/
package com.hung.data.repository;

import com.hung.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<UUID> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
