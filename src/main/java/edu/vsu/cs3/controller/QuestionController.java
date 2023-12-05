package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.dto.response.ResultResponse;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.model.Result;
import edu.vsu.cs3.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size,
                                                               @RequestParam(required = false, defaultValue = "id") String sortParam) {
        return new ResponseEntity<>(questionService.getListOfQuestions(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortParam))).stream()
                .map(question -> modelMapper.map(question, QuestionResponse.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<QuestionResponse>> getQuestionsFilter(@RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size,
                                                               @RequestParam(required = false) String txt) {
        return new ResponseEntity<>(questionService.getListOfQuestions(txt, PageRequest.of(page, size)).stream()
                .map(question -> modelMapper.map(question, QuestionResponse.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(questionService.findById(id), QuestionResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionService.save(modelMapper.map(questionRequest, Question.class));
        return new ResponseEntity<>(modelMapper.map(question, QuestionResponse.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable int id, @RequestBody QuestionRequest questionRequest) {
        Question question = questionService.update(id, modelMapper.map(questionRequest, Question.class));
        return new ResponseEntity<>(modelMapper.map(question, QuestionResponse.class), HttpStatus.OK);
    }
}
