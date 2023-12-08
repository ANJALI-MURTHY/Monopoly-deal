package com.game.monopolydeal.dto;

import jakarta.validation.constraints.*;

public class NewGameForm {

    private Long id;

    @NotEmpty
    private String gameName;

    @NotNull
    @Min(value = 2, message = "Number of players must be greater than or equal to 2.")
    @Max(value = 3, message = "Number of players must be less than or equal to 3.")
    private Integer numPlayers;


    public NewGameForm() {
    }

    public NewGameForm(Long id, String gameName, Integer numPlayers) {
        this.id = id;
        this.gameName = gameName;
        this.numPlayers = numPlayers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }
}
