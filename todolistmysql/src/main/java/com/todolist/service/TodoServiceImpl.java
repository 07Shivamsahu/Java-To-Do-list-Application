package com.todolist.service;

import com.todolist.binding.TodoEntity;
import com.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public String upsert(TodoEntity todoEntity) {

        todoRepository.save(todoEntity); // upsert (update/ insert based on primary key
        return "Success";
    }

    @Override
    public TodoEntity getById(Integer id) {
        Optional<TodoEntity> findById = todoRepository.findById(id);
        if(findById.isPresent()){
            return findById.get();
        }
        return null;
    }

    @Override
    public List<TodoEntity> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public String deleteById(Integer id) {
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return "Delete Success";
        }
        else{
            return "No Record Found";
        }

    }


}
