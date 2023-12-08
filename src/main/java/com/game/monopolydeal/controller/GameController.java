package com.game.monopolydeal.controller;

import com.game.monopolydeal.dto.NewGameForm;
import com.game.monopolydeal.dto.UserDto;
import com.game.monopolydeal.entity.GameMasterData;
import com.game.monopolydeal.entity.User;
import com.game.monopolydeal.repository.CardsDataRepo;
import com.game.monopolydeal.repository.GameMasterDataRepo;
import com.game.monopolydeal.repository.UserRepo;
import com.game.monopolydeal.service.GameDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static java.lang.System.Logger.Level.INFO;

@Controller
public class GameController {

    private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");


    @Autowired
    GameMasterDataRepo gameDataRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    GameDataService gameDataService;

    @Autowired
    private CardsDataRepo cardsRepo;

    @GetMapping("/newgame")
    public String newgame(Model model) {

        NewGameForm newgameform = new NewGameForm();
        model.addAttribute("newgameform", newgameform);

        //get current info
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);
        String name = user.getName();
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        return "newgame";
    }

    @PostMapping("/newgame/start")
    public String newgame(@Valid @ModelAttribute("newgameform") NewGameForm newgameformDto,
                          BindingResult result,
                          Model model, RedirectAttributes redirectAttrs) {

        //first create and save game data to db
        GameMasterData existingGame = gameDataRepo.findByGameName(newgameformDto.getGameName());

        if(existingGame != null && existingGame.getGameStatus().equals("LIVE")){
            result.rejectValue("gameName", null,
                    "There is already an game ongoing with the given name. Please choose a different name");
        }

        if(result.hasErrors()){
            model.addAttribute("newgameform", newgameformDto);
            return "/newgame";
        }

        GameMasterData gameMasterData = gameDataService.saveGameMasterData(newgameformDto);

        if(gameMasterData==null) {
            result.rejectValue("Error", null, "Error in creating game");
            return "/newgame";
        }

        redirectAttrs.addFlashAttribute("roomnumber", gameMasterData.getGameID());
        return "redirect:/waitingscreen";
    }

    /*
    @GetMapping("/findgame")
    public String listcurrentgames(Model model) {

        List<GameMasterData> ans = gameDataService.getAllAvailableGames();
        model.addAttribute("availableGames", ans);

        //get current info
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);
        String name = user.getName();
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        return "findgame";
    }

    @GetMapping("/waitingscreen")
    public String waitingscreen(Model model) {

        return "waitingscreen";
    }


    @GetMapping("/joinwaitingscreen/{id}")
    public String joinwaitingscreen(@Valid @PathVariable("id") String roomnumber, RedirectAttributes redirectAttrs) {

        redirectAttrs.addFlashAttribute("roomnumber", roomnumber);
        return "redirect:/waitingscreen";

    }
    */


    @GetMapping("/findgame")
    public String listcurrentgames(Model model) {

        List<GameMasterData> ans = gameDataService.getAllAvailableGames();
        model.addAttribute("availableGames", ans);

        //get current info
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);
        String name = user.getName();
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        return "findgame";
    }

    @GetMapping("/waitingscreen")
    public String waitingscreen(Model model) {

        return "waitingscreen";
    }

    @GetMapping("/joinwaitingscreen/{id}")
    public String joinwaitingscreen(@Valid @PathVariable("id") String roomnumber, RedirectAttributes redirectAttrs) {

        LOGGER.log(INFO, "ID: " + roomnumber);
        redirectAttrs.addFlashAttribute("roomnumber", roomnumber);
        return "redirect:/waitingscreen";

    }

    @GetMapping("/screendecide")
    public String decideUserScreen() {

        return "screendecide";
    }

    @GetMapping("/gamescreen/{id}")
    public String gameScreen(@Valid @PathVariable("id") String gameId) {

        return "gamescreen";
    }

}
