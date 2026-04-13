package com.catmanscodes.quiz_service.config;

import com.catmanscodes.quiz_service.dto.QuestionDto;
import com.catmanscodes.quiz_service.dto.QuestionRequestDto;
import com.catmanscodes.quiz_service.dto.QuizResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface FeignTemplate {

    // generate quiz based on request
    @PostMapping("/api/questions/generate")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestBody QuestionRequestDto questionRequestDto);

    //get questions for quiz
    @PostMapping("/api/questions/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds);

    //get score for quiz submission
    @PostMapping("/api/questions/getScore")
    public ResponseEntity<Integer> getScoreForQuizSubmission(@RequestBody List<QuizResponseDto> quizResponseDto);
}
