package com.adylbek.wheel_of_fortune.repository;

import com.adylbek.wheel_of_fortune.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByQuestionTextContainingIgnoreCaseOrAnswerWordContainingIgnoreCase(
            String questionText,
            String answerWord,
            Pageable pageable
    );
}