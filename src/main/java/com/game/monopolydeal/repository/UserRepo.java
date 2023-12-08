package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}