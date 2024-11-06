package com.example.ws_projekt.Repository;


import com.example.ws_projekt.Model.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

