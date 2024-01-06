package com.todolist.repository;

import com.todolist.binding.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Serializable> {


}
