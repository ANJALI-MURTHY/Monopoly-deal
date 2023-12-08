package com.game.monopolydeal.repository;

import com.game.monopolydeal.entity.CardsData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsDataRepo extends JpaRepository<CardsData, Integer> {
    List<CardsData> findAll();
}