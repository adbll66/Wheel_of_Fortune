package com.adylbek.wheel_of_fortune.dto;

import lombok.Data;

@Data
public class QuestionRequestDto {
    private String questionText;
    private String answerWord;
}