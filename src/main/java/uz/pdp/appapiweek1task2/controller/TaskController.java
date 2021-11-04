package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.TaskDTO;
import uz.pdp.appapiweek1task2.service.ProgrammingLanguageService;
import uz.pdp.appapiweek1task2.service.TaskService;

@RestController
@RequestMapping("/api/category")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public HttpEntity<?> getTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTask(@PathVariable Integer id){
        Task task = taskService.getTask(id);
        if (task==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public HttpEntity<?> addTask(@RequestBody TaskDTO taskDTO){
        ApiResponse apiResponse = taskService.addTask(taskDTO);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTask(@RequestBody TaskDTO taskDTO, @PathVariable Integer id){
        ApiResponse apiResponse = taskService.editTask(taskDTO, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
