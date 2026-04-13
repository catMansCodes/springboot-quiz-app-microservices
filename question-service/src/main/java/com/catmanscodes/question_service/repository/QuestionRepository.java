package com.catmanscodes.question_service.repository;

import com.catmanscodes.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT q.id FROM questions q WHERE q.category = :category AND q.difficulty = :difficulty ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategoryAndDifficulty(String category, String difficulty, int numberOfQuestions);
}
