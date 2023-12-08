package com.game.monopolydeal.controller;


import com.game.monopolydeal.dto.DisplayCard;
import com.game.monopolydeal.dto.GameMoveDto;
import com.game.monopolydeal.repository.UserRepo;
import com.game.monopolydeal.service.GameDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.Logger.Level.ERROR;

@RestController
public class GameApiController {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    UserRepo userRepo;

    private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");


    @GetMapping("/join/game/{id}")
    public boolean joingaame(@Valid @PathVariable("id") String roomnumber, RedirectAttributes redirectAttrs) {

        //process data according to the message type
        boolean joinstate = gameDataService.joinGame(Integer.parseInt(roomnumber));

        return joinstate;
    }

    @GetMapping("/checkGameReady/{id}")
    public boolean checkGameReadyToPlay(@Valid @PathVariable("id") String roomnumber) {

        boolean isGameReady = gameDataService.checkGameStatusReadyToPLay(Integer.parseInt(roomnumber));

        return isGameReady;

    }

    @GetMapping("/existingGame")
    public Integer checkExistingGame() {

        return gameDataService.getOngoingGame();

    }

    @GetMapping("/getcards/{id}")
    public List<DisplayCard> getStartingCards(@Valid @PathVariable("id") Integer gameId) {

        return gameDataService.getMyStartingCards(gameId);
    }


    @PostMapping("/gameplay")
    public boolean processGamePlayMove(@Valid @ModelAttribute("gameMove") GameMoveDto gameMoveDto) {

        //first check if the user is valid gamer of that gameplay

        Integer currentGame = gameDataService.getOngoingGame();
        if(currentGame!=gameMoveDto.getGameId()) {
            LOGGER.log(ERROR, "Invalid entry to the game");
            return false;
        }
        else {
            //process gameplay move

        }
        return true;
    }



}
