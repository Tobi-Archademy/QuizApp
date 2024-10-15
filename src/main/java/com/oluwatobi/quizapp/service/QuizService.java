package com.oluwatobi.quizapp.service;

import com.oluwatobi.quizapp.model.Question;
import com.oluwatobi.quizapp.model.QuestionWrapper;
import com.oluwatobi.quizapp.model.Quiz;
import com.oluwatobi.quizapp.model.ResultResponse;
import com.oluwatobi.quizapp.repository.QuestionDao;
import com.oluwatobi.quizapp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public String createQuiz(String category, String title, int numQ) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return "Quiz created successfully";
    }

    public List<QuestionWrapper> getQuizQuestionById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper questionWrapper =
                    new QuestionWrapper(
                            q.getId(),
                            q.getQuestionTitle(),
                            q.getOption1(),
                            q.getOption2(),
                            q.getOption3(),
                            q.getOption4());
            questionsForUser.add(questionWrapper);
        }

        return questionsForUser;
    }

    public Integer calculateResult(int id, List<ResultResponse> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int rightAnswer = 0;
        int inc = 0;
        for (ResultResponse response : responses) {
            if (response.getResponse().equals(questions.get(inc).getRightAnswer())) {
                rightAnswer++;
            }
            
            inc++;
        }
        return rightAnswer;
    }
}
