package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.FormResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionController(QuestionService questionService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return new ResponseEntity<>(questionService.getListOfQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionResponse.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(questionService.findById(id), QuestionResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpStatus createQuestion(@RequestBody QuestionRequest questionRequest) {
        questionService.save(modelMapper.map(questionRequest, Question.class));
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
        return HttpStatus.OK;
    }
}
