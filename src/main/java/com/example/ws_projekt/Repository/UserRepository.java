package com.example.ws_projekt.Repository;


import com.example.ws_projekt.Model.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

