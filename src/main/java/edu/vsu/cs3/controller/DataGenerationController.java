package edu.vsu.cs3.controller;

import edu.vsu.cs3.service.RandomDataGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataGenerationController {
    @Autowired
    private RandomDataGeneratorService randomDataGeneratorService;

    @PostMapping("/generate/{count}")
    public void generateRandomData(@PathVariable int count) {
        randomDataGeneratorService.generateTestData(count);
    }
}
