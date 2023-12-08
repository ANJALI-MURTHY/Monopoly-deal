package com.game.monopolydeal.websocket;

public class JoinGameDto {

    private String messageType;


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public JoinGameDto(String messageType) {
        this.messageType = messageType;
    }

    public JoinGameDto() {
    }
}
