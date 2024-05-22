package com.example.driverschool.repository;


import com.example.driverschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Override
    Optional<User> findById(Long aLong);

    boolean existsByUsername(String username);
}

