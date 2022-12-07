package com.skypro.course3.service;

import com.skypro.course3.exceptions.NotEnoughQuestionsException;
import com.skypro.course3.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random RANDOM = new Random();

    @Override
    public Question add(String question, String answer) {
        Question tempQuestion = new Question(question, answer);
        questions.add(tempQuestion);
        return tempQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NotEnoughQuestionsException();
        }
        return questions.stream()
                .skip(RANDOM.nextInt(questions.size()))
                .findFirst()
                .orElseThrow();
    }
}
