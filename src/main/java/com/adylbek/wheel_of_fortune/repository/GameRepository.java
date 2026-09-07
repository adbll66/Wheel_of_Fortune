package com.adylbek.wheel_of_fortune.repository;

import com.adylbek.wheel_of_fortune.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}