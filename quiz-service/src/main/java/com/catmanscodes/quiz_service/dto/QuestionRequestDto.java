package com.catmanscodes.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {

    private String title;
    private String description;
    private String category;
    private String difficulty;
    private Integer numberOfQuestions;

}
