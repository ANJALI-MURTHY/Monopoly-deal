package com.game.monopolydeal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ongoing_games")
public class UserOngoingGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameMasterData gameId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    public UserOngoingGames(Integer id, GameMasterData gameId, User userId) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
    }

    public UserOngoingGames() {
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

    public User getUser_id() {
        return userId;
    }

    public void setUser_id(User userId) {
        this.userId = userId;
    }
}
