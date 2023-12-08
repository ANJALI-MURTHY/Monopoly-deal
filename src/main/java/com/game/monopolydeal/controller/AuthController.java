package com.game.monopolydeal.controller;

import com.game.monopolydeal.dto.UserDto;
import com.game.monopolydeal.entity.CardsData;
import com.game.monopolydeal.entity.User;
import com.game.monopolydeal.repository.CardsDataRepo;
import com.game.monopolydeal.repository.UserRepo;
import com.game.monopolydeal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.Logger.Level.*;

@Controller
public class AuthController {

    private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");

    @Autowired
    UserRepo userRepo;

    @Autowired
    private CardsDataRepo cardsRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {


            List<CardsData> listUsers = cardsRepo.findAll();
            List<String> ans = listUsers.stream().map(CardsData::getCardsDescription).collect(Collectors.toList());
            model.addAttribute("tasks", ans);
            return "index";


    }

    @GetMapping("/welcome")
    public String welcome(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {

        //get current info
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        User user = userRepo.findByEmail(email);
        String name = user.getName();
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        return "welcome";
    }

    @GetMapping("/login")
    public String login(Model model) {

        LOGGER.log(INFO, "Inside login controller");

        System.out.println(model);
//        List<CardsData> listUsers = cardsRepo.findAll();
//        List<String> ans = listUsers.stream().map(CardsData::getCardsDescription).collect(Collectors.toList());
//        model.addAttribute("tasks", ans);
        return "login"; //login.html


    }

    //registration display
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    //registration save operation to db
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

}