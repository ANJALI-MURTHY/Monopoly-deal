package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.GameMovesData;
import com.game.monopolydeal.entity.User;
import com.game.monopolydeal.entity.UserOngoingGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserOngoingGamesRepo extends JpaRepository<UserOngoingGames, Integer> {

    UserOngoingGames findByUserId(Long userId);
}
