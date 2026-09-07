package com.adylbek.wheel_of_fortune.controller;

import com.adylbek.wheel_of_fortune.dto.LetterRequestDto;
import com.adylbek.wheel_of_fortune.dto.WordGuessRequestDto;
import com.adylbek.wheel_of_fortune.entity.Game;
import com.adylbek.wheel_of_fortune.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> startNewGame(@RequestParam String login) {
        Game game = gameService.startNewGame(login);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/letters")
    public ResponseEntity<Game> openLetter(@PathVariable Long gameId, @RequestBody LetterRequestDto request) {
        Game game = gameService.makeMove(gameId, request.getLetter());
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/guess")
    public ResponseEntity<Game> guessWord(@PathVariable Long gameId, @RequestBody WordGuessRequestDto request) {
        // Здесь можно вызвать метод сервиса для проверки слова целиком, когда он будет готов
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameState(@PathVariable Long gameId) {
        // Возвращаем текущее состояние игры
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    public ResponseEntity<?> getGameHistory(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return ResponseEntity.ok().build();
    }
}