package com.nissan.tutorial.controller;

import com.nissan.tutorial.model.Question;
import com.nissan.tutorial.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    // "/surveys/{surveyId}/questions"
    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId) {
        return surveyService.retrieveQuestions(surveyId);
    }

    // "/surveys/{surveyId}/questions"
    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<?> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {
        Question question = surveyService.addQuestion(surveyId, newQuestion);

        if (question == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // "/surveys/{surveyId}/questions/{questionId}"
    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveDetailsForQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }
}
