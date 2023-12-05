package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.AnswerRequest;
import edu.vsu.cs3.dto.response.AnswerResponse;
import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AnswerResponse>> getAnswers() {
        return new ResponseEntity<>(answerService.getListOfAnswers().stream()
                .map(form -> modelMapper.map(form, AnswerResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse> getAnswer(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(answerService.findById(id), AnswerResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpStatus createAnswer(@RequestBody AnswerRequest answerRequest) {
        answerService.save(modelMapper.map(answerRequest, Answer.class));
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAnswer(@PathVariable int id) {
        answerService.delete(id);
        return HttpStatus.OK;
    }
}
