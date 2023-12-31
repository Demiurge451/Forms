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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FormResponse>> getForms() {
        return new ResponseEntity<>(formService.getListOfForms().stream()
                .map(form -> modelMapper.map(form, FormResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormResponse> getForm(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(formService.findById(id), FormResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpStatus createForm(@RequestBody FormRequest formRequest) {
        formService.save(modelMapper.map(formRequest, Form.class));
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteForm(@PathVariable int id) {
        formService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormResponse> updateForm(@PathVariable int id, @RequestBody FormRequest formRequest) {
        Form form = formService.update(id, modelMapper.map(formRequest, Form.class));
        return new ResponseEntity<>(modelMapper.map(form, FormResponse.class), HttpStatus.OK);
    }
}
