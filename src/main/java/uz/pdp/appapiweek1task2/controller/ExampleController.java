package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.Category;
import uz.pdp.appapiweek1task2.entity.Example;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.CategoryDTO;
import uz.pdp.appapiweek1task2.payload.ExampleDTO;
import uz.pdp.appapiweek1task2.service.CategoryService;
import uz.pdp.appapiweek1task2.service.ExampleService;

@RestController
@RequestMapping("/api/category")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    @GetMapping
    public HttpEntity<?> getExamples(){
        return ResponseEntity.ok(exampleService.getExamples());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getExample(@PathVariable Integer id){
        Example example = exampleService.getExample(id);
        if (example==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(example);
    }

    @PostMapping
    public HttpEntity<?> addExample(@RequestBody ExampleDTO exampleDTO){
        ApiResponse apiResponse = exampleService.addExample(exampleDTO);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editExample(@RequestBody ExampleDTO exampleDTO, @PathVariable Integer id){
        ApiResponse apiResponse = exampleService.editExample(exampleDTO, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
