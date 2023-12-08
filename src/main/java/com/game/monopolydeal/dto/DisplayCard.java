package com.game.monopolydeal.dto;

public class DisplayCard {

    private Integer cardId;
    private String cardName;
    private String cardImg;

    public DisplayCard() {
    }

    public DisplayCard(Integer cardId, String cardName, String cardImg) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardImg = cardImg;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }
}
