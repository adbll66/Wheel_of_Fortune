package com.adylbek.wheel_of_fortune.service;

import com.adylbek.wheel_of_fortune.dto.QuestionRequestDto;
import com.adylbek.wheel_of_fortune.dto.QuestionResponseDto;
import com.adylbek.wheel_of_fortune.entity.Question;
import com.adylbek.wheel_of_fortune.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminQuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponseDto createQuestion(QuestionRequestDto request) {
        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setAnswerWord(request.getAnswerWord());
        Question saved = questionRepository.save(question);
        return QuestionResponseDto.fromEntity(saved);
    }

    public Page<QuestionResponseDto> getAllQuestions(String search, Pageable pageable) {
        Page<Question> questions;
        if (search != null && !search.isEmpty()) {
            questions = questionRepository.findByQuestionTextContainingIgnoreCaseOrAnswerWordContainingIgnoreCase(
                    search, search, pageable
            );
        } else {
            questions = questionRepository.findAll(pageable);
        }
        return questions.map(QuestionResponseDto::fromEntity);
    }

    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Вопрос с ID " + id + " не найден"));
        return QuestionResponseDto.fromEntity(question);
    }

    public QuestionResponseDto updateQuestion(Long id, QuestionRequestDto request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Вопрос с ID " + id + " не найден"));

        question.setQuestionText(request.getQuestionText());
        question.setAnswerWord(request.getAnswerWord());
        Question updated = questionRepository.save(question);
        return QuestionResponseDto.fromEntity(updated);
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Вопрос с ID " + id + " не найден");
        }
        questionRepository.deleteById(id);
    }
}