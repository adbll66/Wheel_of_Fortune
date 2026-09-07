package com.adylbek.wheel_of_fortune.service;

import com.adylbek.wheel_of_fortune.entity.Game;
import com.adylbek.wheel_of_fortune.entity.Question;
import com.adylbek.wheel_of_fortune.entity.User;
import com.adylbek.wheel_of_fortune.repository.GameRepository;
import com.adylbek.wheel_of_fortune.repository.QuestionRepository;
import com.adylbek.wheel_of_fortune.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public Game startNewGame(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + login));

        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("В базе данных нет вопросов!");
        }
        Question question = questions.get(random.nextInt(questions.size()));

        String answer = question.getAnswerWord().toLowerCase();
        String maskedWord = "_".repeat(answer.length());

        Game game = new Game();
        game.setUser(user);
        game.setQuestion(question);
        game.setStatus("IN_PROGRESS");
        game.setMaskedWord(maskedWord);
        game.setOpenedLetters("");
        game.setAttemptsLeft(6); // Исправлено на attemptsLeft
        game.setCreatedAt(LocalDateTime.now());

        return gameRepository.save(game);
    }

    public Game makeMove(Long gameId, char letter) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Игра не найдена"));

        if (!"IN_PROGRESS".equals(game.getStatus())) {
            throw new RuntimeException("Эта игра уже завершена!");
        }

        String answer = game.getQuestion().getAnswerWord().toLowerCase();
        char lowerLetter = Character.toLowerCase(letter);

        if (game.getOpenedLetters().indexOf(lowerLetter) >= 0) {
            throw new RuntimeException("Эта буква уже была названа!");
        }

        game.setOpenedLetters(game.getOpenedLetters() + lowerLetter);

        if (answer.indexOf(lowerLetter) >= 0) {
            StringBuilder newMask = new StringBuilder();
            for (char c : answer.toCharArray()) {
                if (game.getOpenedLetters().indexOf(c) >= 0) {
                    newMask.append(c);
                } else {
                    newMask.append('_');
                }
            }
            game.setMaskedWord(newMask.toString());

            if (!newMask.toString().contains("_")) {
                game.setStatus("WIN");
                game.setFinishedAt(LocalDateTime.now());
            }
        } else {

            game.setAttemptsLeft(game.getAttemptsLeft() - 1);
            if (game.getAttemptsLeft() <= 0) {
                game.setStatus("LOSE");
                game.setMaskedWord(answer);
                game.setFinishedAt(LocalDateTime.now());
            }
        }

        return gameRepository.save(game);
    }
}