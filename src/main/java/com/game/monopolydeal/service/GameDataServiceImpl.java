package com.game.monopolydeal.service;

import com.game.monopolydeal.dto.GameMoveDto;
import com.game.monopolydeal.dto.DisplayCard;
import com.game.monopolydeal.dto.NewGameForm;
import com.game.monopolydeal.entity.*;
import com.game.monopolydeal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.Logger.Level.*;

@Service
public class GameDataServiceImpl implements  GameDataService {

    @Autowired
    GameMasterDataRepo gameMasterDataRepo;

    @Autowired
    GameMovesDataRepo gameMovesRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserOngoingGamesRepo ongoingGamesRepo;

    @Autowired
    CardsDataRepo cardsDataRepo;

    @Autowired
    GameStateRepo gameStateRepo;

    private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");

    public GameMasterData saveGameMasterData(NewGameForm newgameformDto) {
        //get current info
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);

        if(user != null){
            GameMasterData game = new GameMasterData();
            game.setGameName(newgameformDto.getGameName());
            game.setMaxNumOfPlayers(newgameformDto.getNumPlayers());
            game.setGameStatus("LIVE");
            game.setCreatedBy(user);
            game.setCurrentNumOfPlayers(0);
            game = gameMasterDataRepo.save(game);

            return game;
        }
        else {
            LOGGER.log(ERROR, "Session expired, cannot found current user");
            return null;
        }
    }

    public List<GameMasterData> getAllAvailableGames() {

        List<GameMasterData> ans = gameMasterDataRepo.findAllAvailableGames();
        return ans;
    }

    public boolean joinGame(Integer gameId) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        LOGGER.log(INFO, "Inside joinGame");

        User user = userRepo.findByEmail(email);
        if (user != null) {

            LOGGER.log(INFO, "Inside joinGame: user");

            //first get game data
            GameMasterData gameInfo = gameMasterDataRepo.findByGameID(gameId);
            if (gameInfo != null) {

                LOGGER.log(INFO, "Inside joinGame: gameInfo");

                //first check if the player already was connected before
                GameMovesData existingPlayer = gameMovesRepo.findIfUserAlreadyExistsInGame(gameInfo.getGameID(), user.getId());
                if(existingPlayer != null) {
                    LOGGER.log(INFO, "Inside joinGame: existingPlayer");
                    return true;
                }
                else {
                    if (gameInfo.getCurrentNumOfPlayers() < gameInfo.getMaxNumOfPlayers()) {
                        LOGGER.log(INFO, "Inside joinGame: less players true");
                        GameMovesData move = new GameMovesData();
                        move.setGameId(gameInfo);
                        move.setMoveBy(user);
                        move.setMoveDetails("Joined: " + user.getName() );
                        move.setMoveType(1);
                        gameMovesRepo.save(move);
                        gameInfo.setCurrentNumOfPlayers(gameInfo.getCurrentNumOfPlayers() + 1);
                        gameMasterDataRepo.save(gameInfo);
                    } else return false;
                }

            } else return false;
        } else return false;

        return false;
    }

    public boolean checkGameStatusReadyToPLay(Integer gameId){

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        LOGGER.log(INFO, "Inside checkGameStatusReadyToPLay");
        User user = userRepo.findByEmail(email);
        if (user != null) {

            //first get game data
            GameMasterData gameInfo = gameMasterDataRepo.findByGameID(gameId);
            LOGGER.log(INFO, "Inside user func");

            if (gameInfo != null) {
                LOGGER.log(INFO, "Inside gameInfo func");
                if(gameInfo.getCurrentNumOfPlayers()==gameInfo.getMaxNumOfPlayers()) {
                    //make cards available if already not done
                    if(gameStateRepo.findFirstByGameId(gameInfo)==null) {
                        shuffleCards(gameId);
                    }
                    return true;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    public Integer getOngoingGame() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        User user = userRepo.findByEmail(email);
        if (user != null) {

            UserOngoingGames game = ongoingGamesRepo.findByUserId(user.getId());
            if(game!=null) {
                return game.getGameId().getGameID();
            }
            else return 0;
        }
        else return 0;
    }

    public boolean processGameMove(GameMoveDto gameMoveDto) {



        //switch case for game move

        switch (gameMoveDto.getMoveType()) {
            case "RENT":
                System.out.println("RENT");
        }
        return true;
    }

    public void shuffleCards(Integer gameId) {
        LOGGER.log(INFO, "inside shuffle cards");
        //only if game doesn't have any  state; i.e. starting point
        GameMasterData game = gameMasterDataRepo.findByGameID(gameId);
        if(game!=null) {
            LOGGER.log(INFO, "game not null");

            List<CardsData> allCards = cardsDataRepo.findAll();

            //get all the players
            List<User> joinedPlayers = gameMovesRepo.findIfUserAlreadyExistsInGame(gameId).stream().map(p->p.getMoveBy()).collect(Collectors.toList());


//        List<Integer> cardIds = allCards.stream().map(p -> p.getCardID()).collect(Collectors.toList());
            Collections.shuffle(allCards);

            LOGGER.log(INFO, "joinedPlayers : " + joinedPlayers);

            //shuffle, take out 5 and distribute
            for(User u: joinedPlayers) {
                GameState gs = new GameState();

                List<CardsData> userCards = new ArrayList<>();
                for(int i=0; i<5; i++) {
                    CardsData card = allCards.get(0);
                    userCards.add(card);
                    allCards.remove(0);
                }
                gs.setGameId(game);
                gs.setMoveBy(u);
                gs.setCurrentInHandCards(userCards);
                LOGGER.log(INFO, "gs : " + gs.toString());
                gameStateRepo.save(gs);
            }
        }
        LOGGER.log(INFO, "done shuffling cards");


    }

    public List<DisplayCard> getMyStartingCards(Integer gameId) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);

        GameState gs = gameStateRepo.findUserInHandCards(gameId, user.getId());
        List<CardsData> cards = gs.getCurrentInHandCards();
        List<DisplayCard> ans = new ArrayList<>();
        for(CardsData card: cards) {
            DisplayCard cardDto = new DisplayCard();
            cardDto.setCardId(card.getCardID());
            cardDto.setCardName(card.getCardName());
            cardDto.setCardImg(card.getCardName());
            ans.add(cardDto);
        }

        return ans;

    }


}
