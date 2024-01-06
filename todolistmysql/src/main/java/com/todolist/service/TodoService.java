package com.todolist.service;

import com.todolist.binding.TodoEntity;

import java.util.List;

public interface TodoService {

    public String upsert(TodoEntity todoEntity);

    public TodoEntity getById(Integer id);

    public List<TodoEntity> getAllTodo();

    public String deleteById(Integer id);

}
