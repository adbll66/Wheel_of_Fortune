package com.adylbek.wheel_of_fortune.service;

import com.adylbek.wheel_of_fortune.entity.Question;
import com.adylbek.wheel_of_fortune.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("В базе данных нет вопросов!");
        }
        return questions.get(random.nextInt(questions.size()));
    }
}