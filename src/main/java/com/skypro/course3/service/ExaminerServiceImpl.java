package com.skypro.course3.service;

import com.skypro.course3.exceptions.NotEnoughQuestionsException;
import com.skypro.course3.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (javaQuestionService.getAll().size() < amount) {
            throw new NotEnoughQuestionsException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() != amount) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
