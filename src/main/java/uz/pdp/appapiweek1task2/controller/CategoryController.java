package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.Answer;
import uz.pdp.appapiweek1task2.entity.Category;
import uz.pdp.appapiweek1task2.payload.AnswerDTO;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.CategoryDTO;
import uz.pdp.appapiweek1task2.service.AnswerService;
import uz.pdp.appapiweek1task2.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCategory(@PathVariable Integer id){
        Category category = categoryService.getCategory(id);
        if (category==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.addCategory(categoryDTO);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id){
        ApiResponse apiResponse = categoryService.editCategory(categoryDTO, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteAnswer(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
