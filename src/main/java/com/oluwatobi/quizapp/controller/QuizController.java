package com.oluwatobi.quizapp.controller;

import com.oluwatobi.quizapp.model.Question;
import com.oluwatobi.quizapp.model.QuestionWrapper;
import com.oluwatobi.quizapp.model.ResultResponse;
import com.oluwatobi.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam int numQ
    ) {
        return new ResponseEntity<>(quizService.createQuiz(category, title, numQ), HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionById(@PathVariable int id) {
        return new ResponseEntity<>(quizService.getQuizQuestionById(id), HttpStatus.OK);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getQuizQuestionById(@PathVariable int id, @RequestBody List<ResultResponse> responses) {
        return new ResponseEntity<>(quizService.calculateResult(id, responses), HttpStatus.OK);
    }
}
