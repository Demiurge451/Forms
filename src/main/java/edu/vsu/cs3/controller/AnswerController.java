package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.AnswerRequest;
import edu.vsu.cs3.dto.response.AnswerResponse;
import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final ModelMapper modelMapper;


    @Autowired
    public AnswerController(AnswerService answerService, ModelMapper modelMapper) {
        this.answerService = answerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<AnswerResponse>> getAnswers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortParam) {
        return new ResponseEntity<>(answerService.getListOfAnswers(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortParam))).stream()
                .map(form -> modelMapper.map(form, AnswerResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnswerResponse>> getAnswersFilter(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String txt) {
        return new ResponseEntity<>(answerService.getListOfAnswers(txt, PageRequest.of(page, size)).stream()
                .map(form -> modelMapper.map(form, AnswerResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse> getAnswer(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(answerService.findById(id), AnswerResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AnswerResponse> createAnswer(@RequestBody AnswerRequest answerRequest) {
        Answer answer = answerService.save(modelMapper.map(answerRequest, Answer.class));
        return new ResponseEntity<>(modelMapper.map(answer, AnswerResponse.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable int id) {
        answerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable int id, @RequestBody AnswerRequest answerRequest) {
        Answer answer = answerService.update(id, modelMapper.map(answerRequest, Answer.class));
        return new ResponseEntity<>(modelMapper.map(answer, AnswerResponse.class), HttpStatus.OK);
    }
}
