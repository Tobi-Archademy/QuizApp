package com.oluwatobi.quizapp.controller;

import com.oluwatobi.quizapp.model.Question;
import com.oluwatobi.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getByCategory(category), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.deleteQuestion(question), HttpStatus.OK);
    }
}
