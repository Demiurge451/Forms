package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.FormRequest;
import edu.vsu.cs3.dto.request.QuestionRequest;
import edu.vsu.cs3.dto.response.FormResponse;
import edu.vsu.cs3.dto.response.QuestionResponse;
import edu.vsu.cs3.model.Form;
import edu.vsu.cs3.model.Question;
import edu.vsu.cs3.service.FormService;
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
@RequestMapping("api/forms")
public class FormController {
    private final FormService formService;
    private final ModelMapper modelMapper;


    @Autowired
    public FormController(FormService formService, ModelMapper modelMapper) {
        this.formService = formService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<FormResponse>> getForms(@RequestParam(required = false, defaultValue = "0") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size,
                                                       @RequestParam(required = false, defaultValue = "id") String sortParam) {
        return new ResponseEntity<>(formService.getListOfForms(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortParam))).stream()
                .map(form -> modelMapper.map(form, FormResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<FormResponse>> getFormsFilter(@RequestParam(required = false, defaultValue = "0") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size,
                                                       @RequestParam(required = false) String title) {
        return new ResponseEntity<>(formService.getListOfForms(title, PageRequest.of(page, size)).stream()
                .map(form -> modelMapper.map(form, FormResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormResponse> getForm(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(formService.findById(id), FormResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<FormResponse> createForm(@RequestBody FormRequest formRequest) {
        Form form = formService.save(modelMapper.map(formRequest, Form.class));
        return new ResponseEntity<>(modelMapper.map(form, FormResponse.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteForm(@PathVariable int id) {
        formService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormResponse> updateForm(@PathVariable int id, @RequestBody FormRequest formRequest) {
        Form form = formService.update(id, modelMapper.map(formRequest, Form.class));
        return new ResponseEntity<>(modelMapper.map(form, FormResponse.class), HttpStatus.OK);
    }
}