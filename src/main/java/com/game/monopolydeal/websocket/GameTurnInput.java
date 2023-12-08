package com.game.monopolydeal.websocket;

public class GameTurnInput {

    private Integer playedCardId;
    private String playedCardName;
    private Integer numTurnsPlayed;

    public GameTurnInput() {
    }

    public GameTurnInput(Integer playedCardId, String playedCardName, Integer numTurnsPlayed) {
        this.playedCardId = playedCardId;
        this.playedCardName = playedCardName;
        this.numTurnsPlayed = numTurnsPlayed;
    }

    public Integer getPlayedCardId() {
        return playedCardId;
    }

    public void setPlayedCardId(Integer playedCardId) {
        this.playedCardId = playedCardId;
    }

    public String getPlayedCardName() {
        return playedCardName;
    }

    public void setPlayedCardName(String playedCardName) {
        this.playedCardName = playedCardName;
    }

    public Integer getNumTurnsPlayed() {
        return numTurnsPlayed;
    }

    public void setNumTurnsPlayed(Integer numTurnsPlayed) {
        this.numTurnsPlayed = numTurnsPlayed;
    }

    @Override
    public String toString() {
        return "GameTurnInput{" +
                "playedCardId=" + playedCardId +
                ", playedCardName='" + playedCardName + '\'' +
                ", numTurnsPlayed=" + numTurnsPlayed +
                '}';
    }
}
