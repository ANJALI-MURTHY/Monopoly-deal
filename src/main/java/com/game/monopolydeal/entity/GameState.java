package com.game.monopolydeal.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "game_state")
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameMasterData gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private User player;

    @ElementCollection // 1
    @CollectionTable(name = "game_state_display_cards", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "display_cards") // 3
    private List<CardsData> currentDisplayedCards;

    @ElementCollection // 1
    @CollectionTable(name = "game_state_inhand_cards", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "inhand_cards") // 3
    private List<CardsData> currentInHandCards;

    public GameState() {
    }

    public GameState(Integer id, GameMasterData gameId, User player, List<CardsData> currentDisplayedCards, List<CardsData> currentInHandCards) {
        this.id = id;
        this.gameId = gameId;
        this.player = player;
        this.currentDisplayedCards = currentDisplayedCards;
        this.currentInHandCards = currentInHandCards;
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
        return player;
    }

    public void setMoveBy(User player) {
        this.player = player;
    }

    public List<CardsData> getCurrentDisplayedCards() {
        return currentDisplayedCards;
    }

    public void setCurrentDisplayedCards(List<CardsData> currentDisplayedCards) {
        this.currentDisplayedCards = currentDisplayedCards;
    }

    public List<CardsData> getCurrentInHandCards() {
        return currentInHandCards;
    }

    public void setCurrentInHandCards(List<CardsData> currentInHandCards) {
        this.currentInHandCards = currentInHandCards;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", player=" + player +
                ", currentDisplayedCards=" + currentDisplayedCards +
                ", currentInHandCards=" + currentInHandCards +
                '}';
    }
}
