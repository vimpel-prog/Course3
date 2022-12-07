package com.skypro.course3.service;

import com.skypro.course3.exceptions.NotEnoughQuestionsException;
import com.skypro.course3.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    JavaQuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;

    @Test
    void getQuestionsTest() {
        List<Question> questionList = List.of(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        );
        when(questionService.getAll())
                .thenReturn(questionList);
        when(questionService.getRandomQuestion())
                .thenReturn(
                        questionList.get(0),
                        questionList.get(1));
        assertThat(examinerService.getQuestions(2))
                .hasSize(2)
                .containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void whenAmountNotCorrectThrows() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> examinerService.getQuestions(15))
                .isInstanceOf(NotEnoughQuestionsException.class);
    }
}
