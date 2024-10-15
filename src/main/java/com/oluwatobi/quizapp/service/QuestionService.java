package com.oluwatobi.quizapp.service;

import com.oluwatobi.quizapp.model.Question;
import com.oluwatobi.quizapp.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        try {
            return questionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Question> getByCategory(String category) {
        try {
            return questionDao.findByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public String addQuestion(Question question) {
        try {
            questionDao.save(question);
            return "Question successfully created";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String deleteQuestion(Question question) {
        try {
            questionDao.delete(question);
            return "Question successfully deleted";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
