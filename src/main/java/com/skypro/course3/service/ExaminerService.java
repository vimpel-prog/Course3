package com.skypro.course3.service;

import com.skypro.course3.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
