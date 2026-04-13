package com.catmanscodes.quiz_service.service;

import com.catmanscodes.quiz_service.config.FeignTemplate;
import com.catmanscodes.quiz_service.dto.QuestionDto;
import com.catmanscodes.quiz_service.dto.QuestionRequestDto;
import com.catmanscodes.quiz_service.dto.QuizResponseDto;
import com.catmanscodes.quiz_service.entity.Quiz;
import com.catmanscodes.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {

    private final QuizRepository quizRepository;
    private final FeignTemplate feignTemplate;

    public Integer createQuiz(QuestionRequestDto questionRequestDto) {

        log.info("Creating new quiz service called");

        // call question service to generate quiz
        List<Integer> listResponseEntity = feignTemplate.generateQuiz(questionRequestDto).getBody();

        if(listResponseEntity == null || listResponseEntity.isEmpty()){
            throw new RuntimeException("Failed to generate quiz. No questions found.");
        }

        Quiz quiz = new Quiz();

        quiz.setTitle(questionRequestDto.getTitle());
        quiz.setDescription(questionRequestDto.getDescription());
        quiz.setCategory(questionRequestDto.getCategory());
        quiz.setDifficulty(questionRequestDto.getDifficulty());
        quiz.setQuestionIds(listResponseEntity);
        quiz.setNumberOfQuestions(listResponseEntity.size());

        Quiz savedQuiz = quizRepository.save(quiz);

        return savedQuiz.getId();
    }

    public List<QuestionDto> getQuestionsForQuiz(List<Integer> questionIds) {
        log.info("Fetching questions for quiz with ids: {}", questionIds);

        return feignTemplate.getQuestionsForQuiz(questionIds).getBody();
    }

    public Integer getScoreForQuizSubmission(List<QuizResponseDto> quizResponseDto) {
        log.info("Calculating score for quiz submission with responses: {}", quizResponseDto);

        return feignTemplate.getScoreForQuizSubmission(quizResponseDto).getBody();
    }
}
