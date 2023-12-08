package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.GameMasterData;
import com.game.monopolydeal.entity.GameMovesData;
import com.game.monopolydeal.entity.GameState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameStateRepo extends JpaRepository<GameState, Integer> {

    GameState findFirstByGameId(GameMasterData game);


    @Query(value = "select * from game_state where game_id = :gameId and player_id = :userId",nativeQuery = true)
    GameState findUserInHandCards(@Param("gameId") Integer gameId, @Param("userId") Long userId);
}
