package com.game.monopolydeal.service;

import com.game.monopolydeal.dto.DisplayCard;
import com.game.monopolydeal.dto.GameMoveDto;
import com.game.monopolydeal.dto.NewGameForm;
import com.game.monopolydeal.entity.GameMasterData;

import java.util.List;

public interface GameDataService {

    GameMasterData saveGameMasterData(NewGameForm newGameForm);

    List<GameMasterData> getAllAvailableGames();

    boolean joinGame(Integer gameId);

    boolean checkGameStatusReadyToPLay(Integer gameId);

    Integer getOngoingGame();

    boolean processGameMove(GameMoveDto gameMoveDto);

    void shuffleCards(Integer gameId);

    List<DisplayCard> getMyStartingCards(Integer gameId);
}
