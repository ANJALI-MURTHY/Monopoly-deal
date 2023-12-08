package com.game.monopolydeal.dto;

import java.util.List;

public class GameMoveDto {

    private String moveType;
    private Integer playedCardId;
    private Integer gameId;

    public GameMoveDto(String moveType, Integer playedCardId, Integer gameId) {
        this.moveType = moveType;
        this.playedCardId = playedCardId;
        this.gameId = gameId;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public Integer getPlayedCardId() {
        return playedCardId;
    }

    public void setPlayedCardId(Integer playedCardId) {
        this.playedCardId = playedCardId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
