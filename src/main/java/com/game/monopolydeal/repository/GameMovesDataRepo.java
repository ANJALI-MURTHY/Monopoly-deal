package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.GameMasterData;
import com.game.monopolydeal.entity.GameMovesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameMovesDataRepo extends JpaRepository<GameMovesData, Integer> {

    @Query(value = "select * from game_moves_data where game_id = ?1 and move_by = ?2 limit 1;",nativeQuery = true)
    GameMovesData findIfUserAlreadyExistsInGame(Integer gameId, Long userId);

    @Query(value = "select * from game_moves_data where game_id = ?1 and move_type = 1",nativeQuery = true)
    List<GameMovesData> findIfUserAlreadyExistsInGame(Integer gameId);

}
