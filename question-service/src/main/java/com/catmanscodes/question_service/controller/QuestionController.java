package com.catmanscodes.question_service.controller;

import com.catmanscodes.question_service.dto.QuestionDto;
import com.catmanscodes.question_service.dto.QuestionRequestDto;
import com.catmanscodes.question_service.dto.QuizResponseDto;
import com.catmanscodes.question_service.model.Question;
import com.catmanscodes.question_service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        log.info("Fetching all questions");

        List<Question> questions = questionService.getAllQuestions();

        if (questions.isEmpty()) {
            log.warn("No questions found");
            return ResponseEntity.noContent().build();
        }

        log.info("Fetched {} questions successfully", questions.size());

        return ResponseEntity.ok(questions);
    }

    //get question by id

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Integer id){
        log.info("Fetching question with id: {}", id);

        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    //create new question
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        log.info("Creating new question");

        return new ResponseEntity<Question>(questionService.createQuestion(question), HttpStatus.CREATED);
    }

    //update question

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable("id") Integer id){
        log.info("Updating question with id: {}", id);

        return new ResponseEntity<Question>(questionService.updateQuestion(question,id), HttpStatus.OK);
    }

    //delete question
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id){
        log.info("Deleting question with id: {}", id);
        questionService.deleteQuestion(id);

        return new ResponseEntity<>("Question has been deleted successfully", HttpStatus.NO_CONTENT);
    }

    //generate quiz

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestBody QuestionRequestDto questionRequestDto) {
        log.info("generate quiz");
        return ResponseEntity.ok(questionService.getRandomQuestionsByCategoryAndDifficulty(questionRequestDto));
    }

    //get questions for quiz
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds) {
        log.info("Fetching questions for quiz with ids: {}", questionIds);

        return ResponseEntity.ok(questionService.getQuestionsForQuiz(questionIds));
    }

    //get score for quiz submission
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScoreForQuizSubmission(@RequestBody List<QuizResponseDto> quizResponseDto) {
        log.info("Calculating score for quiz submission with responses: {}", quizResponseDto);

        return ResponseEntity.ok(questionService.getScoreForQuizSubmission(quizResponseDto));
    }

}
