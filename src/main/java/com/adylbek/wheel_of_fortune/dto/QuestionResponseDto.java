package com.adylbek.wheel_of_fortune.dto;

import lombok.Data;
import com.adylbek.wheel_of_fortune.entity.Question;

@Data
public class QuestionResponseDto {
    private Long id;
    private String questionText;
    private String answerWord;

    public static QuestionResponseDto fromEntity(Question question) {
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestionText());
        dto.setAnswerWord(question.getAnswerWord());
        return dto;
    }
}