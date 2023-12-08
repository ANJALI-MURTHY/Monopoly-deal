package com.game.monopolydeal.service;

import com.game.monopolydeal.dto.UserDto;
import com.game.monopolydeal.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
