package com.catmanscodes.question_service.service;

import com.catmanscodes.question_service.dto.QuestionDto;
import com.catmanscodes.question_service.dto.QuestionRequestDto;
import com.catmanscodes.question_service.dto.QuizResponseDto;
import com.catmanscodes.question_service.exception.NotFoundException;
import com.catmanscodes.question_service.model.Question;
import com.catmanscodes.question_service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        System.out.println(question);
        return questionRepository.save(question);
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(()-> new NotFoundException("no question found with id: "+id));
    }

    public Question updateQuestion(Question question, Integer id) {

        Question databaseQuestion = questionRepository.findById(id).orElseThrow(()-> new NotFoundException("no question found with id: "+id));

        databaseQuestion.setQuestionText(question.getQuestionText());
        databaseQuestion.setOptionA(question.getOptionA());
        databaseQuestion.setOptionB(question.getOptionB());
        databaseQuestion.setOptionC(question.getOptionC());
        databaseQuestion.setOptionD(question.getOptionD());
        databaseQuestion.setCorrectAnswer(question.getCorrectAnswer());
        databaseQuestion.setCategory(question.getCategory());
        databaseQuestion.setDifficulty(question.getDifficulty());

        questionRepository.save(databaseQuestion);

        return databaseQuestion;
    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    public List<Integer> getRandomQuestionsByCategoryAndDifficulty(QuestionRequestDto questionRequestDto) {

        log.info("Fetching random questions with category and difficulty");

        return questionRepository.findRandomQuestionsByCategoryAndDifficulty(questionRequestDto.getCategory()
               , questionRequestDto.getDifficulty(), questionRequestDto.getNumberOfQuestions());
    }


    public List<QuestionDto> getQuestionsForQuiz(List<Integer> questionIds) {
        log.info("Fetching questions for quiz with ids: {}", questionIds);

        // we need questions from database base on requested ids, then we need to convert them to question dtos and return them

        List<QuestionDto> questionsDtos = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Integer questionId: questionIds) {
            questions.add(this.getQuestionById(questionId));
        }

        // convert questions to question dtos
        for(Question question: questions) {
            QuestionDto questionDto = new QuestionDto();

            questionDto.setId(question.getId());
            questionDto.setQuestionText(question.getQuestionText());
            questionDto.setOptionA(question.getOptionA());
            questionDto.setOptionB(question.getOptionB());
            questionDto.setOptionC(question.getOptionC());
            questionDto.setOptionD(question.getOptionD());

            questionsDtos.add(questionDto);
        }

        return questionsDtos;
    }

    public Integer getScoreForQuizSubmission(List<QuizResponseDto> quizResponseDto) {

        //get questions from database based on quiz response question ids
        List<Question> questions = new ArrayList<>();

        int score = 0;

        for(QuizResponseDto quizResponse: quizResponseDto) {
            Question question= this.getQuestionById(quizResponse.getQuestionId());
            if(quizResponse.getAnswer().equals(question.getCorrectAnswer())){
                score++;
            }
        }

        return score;
    }
}
