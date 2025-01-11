package com.example.app.controller;

import com.example.app.model.ExampleModel;
import com.example.app.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping
    public List<ExampleModel> getAllExamples() {
        return exampleService.getAllExamples();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExampleModel> getExampleById(@PathVariable Long id) {
        return exampleService.getExampleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExampleModel createExample(@RequestBody ExampleModel exampleModel) {
        return exampleService.createExample(exampleModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExampleModel> updateExample(@PathVariable Long id, @RequestBody ExampleModel exampleModel) {
        return exampleService.updateExample(id, exampleModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        if (exampleService.deleteExample(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}