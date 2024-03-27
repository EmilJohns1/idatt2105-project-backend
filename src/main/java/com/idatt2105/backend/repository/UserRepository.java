package com.idatt2105.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idatt2105.backend.model.User;

/** Repository for User entities. */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
