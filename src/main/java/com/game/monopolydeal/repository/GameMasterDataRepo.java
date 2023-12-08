package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.GameMasterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameMasterDataRepo extends JpaRepository<GameMasterData, Integer> {

    GameMasterData findByGameName(String name);

    GameMasterData findByGameID(Integer id);

    @Query(value = "SELECT * FROM game_master_data where game_status = 'LIVE' AND current_num_of_players < max_num_of_players;", nativeQuery = true)
    List<GameMasterData> findAllAvailableGames();
}
