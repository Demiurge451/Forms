package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.ResultRequest;
import edu.vsu.cs3.dto.response.ResultResponse;
import edu.vsu.cs3.model.Result;
import edu.vsu.cs3.service.ResultService;
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
@RequestMapping("api/results")
public class ResultController {
    private final ResultService resultService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultController(ResultService resultService, ModelMapper modelMapper) {
        this.resultService = resultService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResultResponse>> getResults(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortParam) {
        return new ResponseEntity<>(resultService.getListOfResults(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC, sortParam))).stream()
                .map(question -> modelMapper.map(question, ResultResponse.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResult(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(resultService.findById(id), ResultResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> createResult(@RequestBody ResultRequest resultRequest) {
        resultService.save(modelMapper.map(resultRequest, Result.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteResult(@PathVariable int id) {
        resultService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updateResult(@PathVariable int id, @RequestBody ResultRequest resultRequest) {
        Result result = resultService.update(id, modelMapper.map(resultRequest, Result.class));
        return new ResponseEntity<>(modelMapper.map(result, ResultResponse.class), HttpStatus.OK);
    }
}
