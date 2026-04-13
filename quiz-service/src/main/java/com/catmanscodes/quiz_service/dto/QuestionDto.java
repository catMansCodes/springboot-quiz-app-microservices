package com.catmanscodes.quiz_service.dto;

import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

}
