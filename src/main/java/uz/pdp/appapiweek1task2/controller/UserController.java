package uz.pdp.appapiweek1task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.entity.User;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.TaskDTO;
import uz.pdp.appapiweek1task2.service.TaskService;
import uz.pdp.appapiweek1task2.service.UserService;

@RestController
@RequestMapping("/api/category")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        if (user==null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody User user){
        ApiResponse apiResponse = userService.addUser(user);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@RequestBody User user    , @PathVariable Integer id){
        ApiResponse apiResponse = userService.editUser(user, id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);
        return ResponseEntity.status(202).body(apiResponse);
    }
}
