package com.adylbek.wheel_of_fortune.controller;

import com.adylbek.wheel_of_fortune.dto.QuestionRequestDto;
import com.adylbek.wheel_of_fortune.dto.QuestionResponseDto;
import com.adylbek.wheel_of_fortune.service.AdminQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/questions")
@RequiredArgsConstructor
public class AdminQuestionController {

    private final AdminQuestionService adminQuestionService;

    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto request) {
        return ResponseEntity.ok(adminQuestionService.createQuestion(request));
    }

    @GetMapping
    public ResponseEntity<Page<QuestionResponseDto>> getAllQuestions(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(adminQuestionService.getAllQuestions(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(adminQuestionService.getQuestionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(
            @PathVariable Long id,
            @RequestBody QuestionRequestDto request) {
        return ResponseEntity.ok(adminQuestionService.updateQuestion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        adminQuestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}