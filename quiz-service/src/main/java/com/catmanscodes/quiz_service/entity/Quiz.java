package com.catmanscodes.quiz_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "difficulty")
    private String difficulty;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "number_of_questions")
    private int numberOfQuestions;

    @ElementCollection
    private List<Integer> questionIds;

}