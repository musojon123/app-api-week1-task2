package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.Answer;
import uz.pdp.appapiweek1task2.payload.AnswerDTO;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public HttpEntity<?> getAnswers(){
        return ResponseEntity.ok(answerService.getAnswers());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAnswer(@PathVariable Integer id){
        Answer answer = answerService.getAnswer(id);
        if (answer==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @PostMapping
    public HttpEntity<?> addAnswer(@RequestBody AnswerDTO answerDTO){
        ApiResponse apiResponse = answerService.addAnswer(answerDTO);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable Integer id){
        ApiResponse apiResponse = answerService.editAnswer(answerDTO, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
