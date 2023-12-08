package com.game.monopolydeal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "game_master_data")
public class GameMasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    @Column(nullable=false)
    private String gameName;

    @Min(value = 2, message = "Number of players must be greater than or equal to 2.")
    @Max(value = 3, message = "Number of players must be less than or equal to 3.")
    @Column(nullable=false)
    private Integer maxNumOfPlayers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable=false)
    @Pattern(regexp="^(LIVE|FINISHED)$",message="invalid game status")
    private String gameStatus;

    @Column(nullable = false)
    private Integer currentNumOfPlayers;


    public GameMasterData(Integer gameID, String gameName, Integer maxNumOfPlayers, User createdBy, String gameStatus, Integer currentNumOfPlayers) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.maxNumOfPlayers = maxNumOfPlayers;
        this.createdBy = createdBy;
        this.gameStatus = gameStatus;
        this.currentNumOfPlayers = currentNumOfPlayers;
    }

    public GameMasterData() {
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Integer getMaxNumOfPlayers() {
        return maxNumOfPlayers;
    }

    public void setMaxNumOfPlayers(Integer maxNumOfPlayers) {
        this.maxNumOfPlayers = maxNumOfPlayers;
    }

    public Integer getCurrentNumOfPlayers() {
        return currentNumOfPlayers;
    }

    public void setCurrentNumOfPlayers(Integer currentNumOfPlayers) {
        this.currentNumOfPlayers = currentNumOfPlayers;
    }
}
