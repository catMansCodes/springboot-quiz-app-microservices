package com.catmanscodes.quiz_service.controller;

import com.catmanscodes.quiz_service.dto.QuestionDto;
import com.catmanscodes.quiz_service.dto.QuestionRequestDto;
import com.catmanscodes.quiz_service.dto.QuizResponseDto;
import com.catmanscodes.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createQuiz(@RequestBody QuestionRequestDto questionRequestDto) {

        log.info("Creating new quiz");

        return ResponseEntity.ok(quizService.createQuiz(questionRequestDto));
    }

    //get questions for quiz
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds) {
        log.info("Fetching questions for quiz with ids: {}", questionIds);

        return ResponseEntity.ok(quizService.getQuestionsForQuiz(questionIds));
    }

    //get score for quiz submission
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScoreForQuizSubmission(@RequestBody List<QuizResponseDto> quizResponseDto) {
        log.info("Calculating score for quiz submission with responses: {}", quizResponseDto);

        return ResponseEntity.ok(quizService.getScoreForQuizSubmission(quizResponseDto));
    }

}
