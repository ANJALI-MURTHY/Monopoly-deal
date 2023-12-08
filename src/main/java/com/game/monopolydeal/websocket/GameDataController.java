package com.game.monopolydeal.websocket;

import com.game.monopolydeal.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import static java.lang.System.Logger.Level.*;

@Controller
public class GameDataController {

    private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");

    @Autowired
    GameDataService gameDataService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GameTurnInput greeting(GameTurnInput message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GameTurnInput(1, "DEAL BREAKER!", 1);
    }

    @GetMapping("/websocket")
    public String websocketdemo() {

        return "websocketdemo";

    }

    @MessageMapping("/join/{roomnumber}")
    @SendTo("/topic/joinroom/{roomnumber}")
    public String joinDataProcess(@DestinationVariable String roomnumber, JoinGameDto message) throws Exception {

        LOGGER.log(INFO, "INSIDE JOINDATAPROCESS");
//        return new GameTurnInput(1, "DEAL BREAKER!", 1);
        //process data according to the message type
        if(message.getMessageType().equals("joinrequest")) {
            //validate
            boolean joinstate = gameDataService.joinGame(Integer.parseInt(roomnumber));
            if(joinstate) return "success";
            else return "fail";

        }
        return "fail";
    }

    @MessageMapping("/game/{roomnumber}")
    @SendTo("/topic/game/{roomnumber}")
    public String gameDataProcess(@DestinationVariable String roomnumber, JoinGameDto message) throws Exception {
//        return new GameTurnInput(1, "DEAL BREAKER!", 1);
        //process data according to the
        if(message.getMessageType().equals("joinrequest")) {

        }
        return "";
    }

}
