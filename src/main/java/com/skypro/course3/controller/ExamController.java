package com.skypro.course3.controller;

import com.skypro.course3.exceptions.NotEnoughQuestionsException;
import com.skypro.course3.model.Question;
import com.skypro.course3.service.ExaminerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {
    public final ExaminerService examinerService;

    @ExceptionHandler(NotEnoughQuestionsException.class)
    public ResponseEntity<String> handleException() {
        return ResponseEntity
                .badRequest()
                .body("Нет столько вопросов");
    }

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getAllQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
