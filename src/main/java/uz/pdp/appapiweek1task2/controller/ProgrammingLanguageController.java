package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.Example;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.ExampleDTO;
import uz.pdp.appapiweek1task2.service.ExampleService;
import uz.pdp.appapiweek1task2.service.ProgrammingLanguageService;

@RestController
@RequestMapping("/api/category")
public class ProgrammingLanguageController {
    @Autowired
    ProgrammingLanguageService languageService;

    @GetMapping
    public HttpEntity<?> getLanguages(){
        return ResponseEntity.ok(languageService.getLanguages());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getLanguage(@PathVariable Integer id){
        ProgrammingLanguage language = languageService.getLanguage(id);
        if (language==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(language);
    }

    @PostMapping
    public HttpEntity<?> addLanguage(@RequestBody ProgrammingLanguage language){
        ApiResponse apiResponse = languageService.addLanguage(language);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editLanguage(@RequestBody ProgrammingLanguage language, @PathVariable Integer id){
        ApiResponse apiResponse = languageService.editLanguage(language, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
