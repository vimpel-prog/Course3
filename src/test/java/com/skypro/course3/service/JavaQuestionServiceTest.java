package com.skypro.course3.service;

import com.skypro.course3.exceptions.NotEnoughQuestionsException;
import com.skypro.course3.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;
    private final List<Question> questions = List.of(
            new Question("Question1", "Answer1"),
            new Question("Question2", "Answer2"),
            new Question("Question3", "Answer3"),
            new Question("Question4", "Answer4"),
            new Question("Question5", "Answer5")
    );

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
        questions.forEach(javaQuestionService::add);
    }

    @Test
    void addQuestionTest() {
        Question q = new Question("test", "test");
        Question actual = javaQuestionService.add(q);
        assertThat(actual).isEqualTo(q);
        assertThat(javaQuestionService.getAll())
                .hasSize(6)
                .contains(q);
    }

    @Test
    void removeQuestionTest() {
        Question expected = questions.get(0);
        Question actual = javaQuestionService.remove(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(javaQuestionService.getAll())
                .hasSize(4)
                .doesNotContain(expected);
    }

    @Test
    void getRandomQuestionTest() {
        Question actual = javaQuestionService.getRandomQuestion();
        assertThat(actual).isIn(questions);
    }

    @Test
    void getAllTest() {
        assertThat(javaQuestionService.getAll())
                .hasSize(5)
                .containsAll(questions);
    }

    @Test
    void whenListIsEmptyThenGetRandomQuestionReturnsException() {
        questions.forEach(javaQuestionService::remove);
        assertThrows(NotEnoughQuestionsException.class, () -> javaQuestionService.getRandomQuestion());
    }

}
