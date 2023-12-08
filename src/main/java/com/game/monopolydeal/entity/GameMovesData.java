package com.game.monopolydeal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

//move type 1 = join
//move type 2 = game move


@Entity
@Table(name = "game_moves_data")
public class GameMovesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameMasterData gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "move_by")
    private User moveBy;

    @Column(nullable = false)
    private String moveDetails;

    @Column(nullable = false)
    private Integer moveType;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp moveTime;

    public GameMovesData() {
    }

    public GameMovesData(Integer id, GameMasterData gameId, User moveBy, String moveDetails, Integer moveType, Timestamp moveTime) {
        this.id = id;
        this.gameId = gameId;
        this.moveBy = moveBy;
        this.moveDetails = moveDetails;
        this.moveType = moveType;
        this.moveTime = moveTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameMasterData getGameId() {
        return gameId;
    }

    public void setGameId(GameMasterData gameId) {
        this.gameId = gameId;
    }

    public User getMoveBy() {
        return moveBy;
    }

    public void setMoveBy(User moveBy) {
        this.moveBy = moveBy;
    }

    public String getMoveDetails() {
        return moveDetails;
    }

    public void setMoveDetails(String moveDetails) {
        this.moveDetails = moveDetails;
    }

    public Integer getMoveType() {
        return moveType;
    }

    public void setMoveType(Integer moveType) {
        this.moveType = moveType;
    }

    public Timestamp getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(Timestamp moveTime) {
        this.moveTime = moveTime;
    }
}
