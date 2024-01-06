package com.todolist.controller;

import com.todolist.binding.TodoEntity;
import com.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/addTodo")
    public ResponseEntity<String> createTodo(@RequestBody TodoEntity todoEntity) {

        String status = todoService.upsert(todoEntity);
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TodoEntity> getTodo(@PathVariable Integer id){
        TodoEntity todoEntity = todoService.getById(id);
        return new ResponseEntity<>(todoEntity, HttpStatus.OK);
    }

    @GetMapping("/allTodo")
    public ResponseEntity<List<TodoEntity>> getAllTodo(){
        List<TodoEntity> allTodo = todoService.getAllTodo();
        return new ResponseEntity<>(allTodo, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTodo(@RequestBody TodoEntity todoEntity) {
        String status = todoService.upsert(todoEntity);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id){
        String status = todoService.deleteById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
